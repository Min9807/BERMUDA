package com.example.bermuda.service;

import com.example.bermuda.domain.Music;
import com.example.bermuda.domain.User;
import com.example.bermuda.dto.MusicDto;
import com.example.bermuda.exception.exceptions.CUserNotFoundException;
import com.example.bermuda.repository.MusicRepository;
import com.example.bermuda.repository.UserRepository;
import com.example.bermuda.utils.FileHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class UploadService {
    private final UserRepository userRepository;
    private final FileHandler fileHandler;

    private final MusicRepository musicRepository;
    @Transactional
    public void uploadMusicByLoginId(String loginId, MultipartFile uploadMusic, String songName, String mood) throws IOException {
        MusicDto.RepMusicDto repMusicDto;

        // currentId가 로그인된 사용자 인지 확인
        User loginUser = userRepository.findByUserId(loginId).orElseThrow(CUserNotFoundException::new);
        log.info(loginUser.getUserId());
        //ProfileImageChangeDto profileImageChangeDto = profileImageManage.uploadImage(loginUser, profileImageFile);

        //프로필 사진이 존재하지 않는 경우 사진도 추가하고 관계도 형성
        repMusicDto = fileHandler.uploadMusic(loginUser, uploadMusic, songName, mood);
        Music music = Music.builder()
                .originalFileName(repMusicDto.getOriginalFileName())
                .storedFilePath(repMusicDto.getStoredFilePath())
                .fileSize(repMusicDto.getFileSize())
                .mood(repMusicDto.getMood())
                .user(loginUser)
                .atmosphereDrum(repMusicDto.getAtmosphereDrum())
                .atmosphereCode(repMusicDto.getAtmosphereCode())
                .background(repMusicDto.getBackground())
                .build();
        musicRepository.save(music);


    }
}
