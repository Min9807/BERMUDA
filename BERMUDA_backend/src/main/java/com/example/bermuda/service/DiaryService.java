package com.example.bermuda.service;

import com.example.bermuda.converter.DiaryDtoConverter;
import com.example.bermuda.domain.*;
import com.example.bermuda.dto.*;
import com.example.bermuda.exception.exceptions.CDiaryNotFoundException;
import com.example.bermuda.exception.exceptions.CUserNotFoundException;
import com.example.bermuda.repository.*;
import com.example.bermuda.utils.GeometryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@Service
public class DiaryService {

    final private DiaryRepository diaryRepository;
    final private LocationRepository locationRepository;
    final private UserRepository userRepository;
    private final DiaryDtoConverter diaryDtoConverter;
    private final UserDiaryRepository userDiaryRepository;
    //final private ImageManageService imageManageService;
    private final MusicRepository musicRepository;
    // 통신을 하기 위한.
    private RestTemplate restTemplate;
    @Transactional
    public DiaryDto.ShowDiaryDto getDiaryByDiaryId(Long diaryId){
        //다이어리 엔티티 가져오기
        Diary diary = diaryRepository.findByDiaryId(diaryId).orElseThrow(CDiaryNotFoundException::new);

        //checkUser에서 본인인지 아닌지를 판별합니다. 본인일 경우 편집 버튼을 활성화 해서 updatediary로 이동이 가능해집니다.
        return diaryDtoConverter.toShowDiaryDto(diary, diary.checkUser(true));
    }

    @Transactional
    public void saveDiary(String userId, DiaryDto.CreateDiaryDto createDiaryDto) {
        LocationDto.RepLocationDto locationDto = createDiaryDto.getRepLocationDto();

        //일기장 db에 저장할 유저를 가져옵니다.
        User user = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new);

        //일기장에 저장합니다.
        Diary diary = diaryRepository.save(
                Diary.builder()
                        .user(user)
                        .writeDate(LocalDate.now())
//                        .title(createDiaryDto.getTitle())
                        .content(createDiaryDto.getContent())
                        .open(createDiaryDto.getOpen())
                        .likeNum(0)
                        .build()
        );


        /* 일기 내용 분석하는 부분 Flask와 통신 */

        // 저장한 일기장에서 본문(diary.getContent)만 가져와서 객체 생성
        DiaryDto.SendContentDto request_content = new DiaryDto.SendContentDto(diary.getContent());
        // 플라스크로 보내는 서비스 동작
        String getMood = sendEngine(request_content); // 플라스크로부터 가져온 감성분석한 결과(background)

        Music music = musicRepository.findByMood(getMood);
        music.addDiaryList(diary);

//        musicRepository.

        Location location;
        // 1번 안
        // 주소 단위로 검색을 했을 때 겹치는 곳이 있으면 불러오고 그렇지 않으면 해당 지역에 대한 db를 생성
        if(locationRepository.existsByAddressName(locationDto.getAddressName())) {
            location = locationRepository.findByAddressName(locationDto.getAddressName()).get();
        }else{
            location = locationRepository.save(
                    Location.builder()
                            .addressName(locationDto.getAddressName())
                            //.categoryGroupCode(locationDto.getCategoryGroupCode())
                            //.categoryGroupName(locationDto.getCategoryGroupName())
                            //.categoryName(locationDto.getCategoryName())
                            //.phone(locationDto.getPhone())
                            //.placeName(locationDto.getPlaceName())
                            //.placeUrl(locationDto.getPlaceUrl())
                            .point(GeometryUtils.create(locationDto.getLatitude(), locationDto.getLongitude()))
                            //.roadAddressName(locationDto.getRoadAddressName())
                            .build()
            );
        }

        // 2번 안
//        //위도, 경도 단위로 검색을 하고 겹치는 곳이 있으면 불러오고 그렇지 않으면 해당 지역에 대한 db 생성
//        Location location = locationRepository.findByLatitudeAndLongitude(locationDto.getLatitude(), locationDto.getLongitude()).orElse(
//                locationRepository.save(
//                        Location.builder()
//                                .addressName(locationDto.getAddressName())
//                                .categoryGroupCode(locationDto.getCategoryGroupCode())
//                                .categoryGroupName(locationDto.getCategoryGroupName())
//                                .categoryName(locationDto.getCategoryName())
//                                .phone(locationDto.getPhone())
//                                .placeName(locationDto.getPlaceName())
//                                .placeUrl(locationDto.getPlaceUrl())
//                                .point(locationDto.getPoint())
//                                .roadAddressName(locationDto.getRoadAddressName())
//                                .build()
//                )
//        );

//        //diarylist에 대해서는 양방향 manytoone 이기 때문에 여기서 추가하는게 아님
//        Location location = locationRepository.save(
//                Location.builder()
//                        .addressName(locationDto.getAddressName())
//                        .categoryGroupCode(locationDto.getCategoryGroupCode())
//                        .categoryGroupName(locationDto.getCategoryGroupName())
//                        .categoryName(locationDto.getCategoryName())
//                        .phone(locationDto.getPhone())
//                        .placeName(locationDto.getPlaceName())
//                        .placeUrl(locationDto.getPlaceUrl())
//                        .point(locationDto.getPoint())
//                        .roadAddressName(locationDto.getRoadAddressName())
//                        .build()
//        );

        //두개 모두 db에 반영된 후에 작업해주면 됨.
        location.addDiaryList(diary);
//        music.addDiaryList(diary);
    }

    public String sendEngine(DiaryDto.SendContentDto sendContentDto) {

        restTemplate = new RestTemplate();

        DiaryDto.SendContentDto requestDto = DiaryDto.SendContentDto.builder()
                        .content(sendContentDto.getContent())
                        .build();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<DiaryDto.SendContentDto> entity = new HttpEntity<>(requestDto, headers);

        String url = "http://localhost:5000/tospring";

        return restTemplate.postForObject(url, entity, String.class);
    }

    @Transactional
    public DiaryDto.DiaryResultDto updateContentAndOpenByDiaryId(DiaryDto.UpdateDiaryDto updateDiaryDto, Long diaryId) {
        //수정할 일기장 불러오기
        Diary diary = diaryRepository.findById(diaryId).orElseThrow(CDiaryNotFoundException::new);

        //일기의 유저와 로그인한 유저의 정보가 같은지 비교
        diary.checkUser();
        //엔티티에 업데이트할 값들
        diary.update(updateDiaryDto.getContent(), updateDiaryDto.getOpen());

        return diaryDtoConverter.toDiaryResultDto(diary);
    }

    @Transactional
    public DiaryDto.DiaryResultDto deleteByDiaryId(Long diaryId){
        Diary diary = diaryRepository.findByDiaryId(diaryId).orElseThrow(CDiaryNotFoundException::new);

        diary.checkUser();                                                                          //일기의 유저와 로그인한 유저의 정보가 같은지 비교

        diaryRepository.deleteByDiaryId(diaryId).orElseThrow(CDiaryNotFoundException::new);
        return diaryDtoConverter.toDiaryResultDto(diary);
    }

    @Transactional
    public void updateLike(String loginId, Long diaryId, Boolean liked){
        User user = userRepository.findByUserId(loginId).orElseThrow(CUserNotFoundException::new);
        Diary diary = diaryRepository.findByDiaryId(diaryId).orElseThrow(CDiaryNotFoundException::new);

        if (liked){
            userDiaryRepository.save(UserDiary.builder()
                    .diary(diary)
                    .user(user)
                    .build()
            );
        }else{
            userDiaryRepository.deleteByUserAndDiary(user, diary);
        }
    }
}