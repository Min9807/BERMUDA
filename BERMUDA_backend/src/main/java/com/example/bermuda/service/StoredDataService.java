package com.example.bermuda.service;

import com.example.bermuda.converter.DiaryDtoConverter;
import com.example.bermuda.converter.ProfileImageDtoConverter;
import com.example.bermuda.converter.VoiceActorDtoConverter;
import com.example.bermuda.domain.*;
import com.example.bermuda.dto.DiaryDto;
import com.example.bermuda.dto.VoiceActorDto;
import com.example.bermuda.exception.exceptions.CDiaryNotFoundException;
import com.example.bermuda.exception.exceptions.CUserNotFoundException;
import com.example.bermuda.exception.exceptions.CVoicActorNotFoundException;
import com.example.bermuda.repository.*;
import com.example.bermuda.utils.FileHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoredDataService {

    private final UserDiaryRepository userDiaryRepository;
    private final UserVoiceActorRepository userVoiceActorRepository;
    private final UserMusicRepository userMusicRepository;
    private final UserRepository userRepository;
    private final ProfileImageRepository profileImageRepository;
    private final FileHandler fileHandler;

    @Transactional
    public List<DiaryDto.DiaryPreviewDto> getStoredDiaryDtoListByLoginId(String loginId) throws IOException {
        User loginUser = userRepository.findByUserId(loginId).orElseThrow(CUserNotFoundException::new);
        //우선 userDiary를 먼저 받아옴.
        List<UserDiary> userDiaryList = userDiaryRepository.findAllByUser(loginUser).orElseThrow(CDiaryNotFoundException::new);

        List<DiaryDto.DiaryPreviewDto> diaryPreviewDtoList = new ArrayList<>();
        User user;
        ProfileImage profileImage;
        //검색된 userDiary에서 diary만 추출
        for (UserDiary userDiary : userDiaryList) {
                diaryPreviewDtoList.add(DiaryDtoConverter.toDiaryPreviewDto(userDiary.getDiary(), loginId, true));
                log.info(userDiary.getDiary().getContent());
        }
        return diaryPreviewDtoList;
    }

    @Transactional
    public List<VoiceActorDto.StoredVoiceActorDto> getStoredVoiceActorDtoListByLoginId(String loginId) {
        User loginUser = userRepository.findByUserId(loginId).orElseThrow(CUserNotFoundException::new);
        //우선 userDiary를 먼저 받아옴.
        List<UserVoiceActor> userVoiceActorList = userVoiceActorRepository.findAllByUser(loginUser).orElseThrow(CVoicActorNotFoundException::new);

        List<VoiceActorDto.StoredVoiceActorDto> storedVoiceActorDtoList = new ArrayList<>();

        //검색된 userDiary에서 diary만 추출
        for (UserVoiceActor userVoiceActor : userVoiceActorList) {
            storedVoiceActorDtoList.add(VoiceActorDtoConverter.toStoredVoiceActor(userVoiceActor.getVoiceActor()));
        }
        return storedVoiceActorDtoList;
    }
//    @Transactional
//    public List<MusicDto.StoredMusicDto> getStoredMusicDtoListByLoginId(String loginId){
//        User loginUser = userRepository.findByUserId(loginId).orElseThrow(CUserNotFoundException::new);
//        //우선 userDiary를 먼저 받아옴.
//        List<UserMusic> userMusicList = userMusicRepository.findAllByUser(loginUser).orElseThrow(CMusicNotFoundException::new);
//
//        List<MusicDto.StoredMusicDto> storedMusicDtoList = new ArrayList<>();
//
//        //검색된 userDiary에서 diary만 추출
//        for (UserMusic userMusic : userMusicList){
//            storedMusicDtoList.add(MusicDtoConverter.toStoredMusic(userMusic.getMusic()));
//        }
//        return storedMusicDtoList;
//    }
}
