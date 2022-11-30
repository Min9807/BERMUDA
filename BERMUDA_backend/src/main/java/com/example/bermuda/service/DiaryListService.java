package com.example.bermuda.service;

import com.example.bermuda.converter.DiaryDtoConverter;
import com.example.bermuda.domain.Diary;
import com.example.bermuda.domain.Location;
import com.example.bermuda.domain.User;
import com.example.bermuda.dto.DiaryDto;
import com.example.bermuda.dto.LocationDto;
import com.example.bermuda.exception.exceptions.CUserNotFoundException;
import com.example.bermuda.repository.DiaryRepository;
import com.example.bermuda.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class DiaryListService {
    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;
    private final LocationService locationService;

    @Transactional
    public List<DiaryDto.DiaryPreviewDto> getMyDiaryListByUserId(String userId, Pageable pageable) {
        // 서비스에서도 db에서 꺼내는 것 정도는 뭐...
        User user = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new);

        if(user.getMyDiaryList().size() == 0)
            return null;

        log.info("log for jpa diary paging");
        log.info(" " + user.getMyDiaryList().get(0).getDiaryId());

        List<Diary> diaryList = diaryRepository.findByUser(user, pageable).orElseThrow();

        // db에서 받은 일기 미리보기 리스트를 dto로 변환하기 위한 리스트
        List<DiaryDto.DiaryPreviewDto> diaryPreviewDtoList = new ArrayList<>();

        // dto리스트로 변환
        for (Diary diary : diaryList) {
            diaryPreviewDtoList.add(DiaryDtoConverter.toDiaryPreviewDto(diary, userId, false));
        }

        return diaryPreviewDtoList;
    }

    @Transactional
    public List<DiaryDto.DiaryPreviewDto> getNearDiaryByLocationDto(double latitude, double longitude, String loginId) throws IOException {
        // db에서 받은 일기 미리보기 리스트를 dto로 변환하기 위한 리스트 (대신 여기선 Location을 받아올 필요는 없다.)
        List<DiaryDto.DiaryPreviewDto> diaryPreviewDtoList = new ArrayList<>();

        List<DiaryDto.DiaryPreviewDto> nearDiaryPreviewList = locationService.getLocationDtoByLatitudeAndLongitude(latitude, longitude, loginId);

        return nearDiaryPreviewList;
    }
}
