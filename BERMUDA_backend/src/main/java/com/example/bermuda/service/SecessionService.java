package com.example.bermuda.service;

import com.example.bermuda.domain.User;
import com.example.bermuda.domain.UserDiary;
import com.example.bermuda.domain.UserMusic;
import com.example.bermuda.domain.UserVoiceActor;
import com.example.bermuda.exception.exceptions.CUserNotFoundException;
import com.example.bermuda.repository.UserDiaryRepository;
import com.example.bermuda.repository.UserMusicRepository;
import com.example.bermuda.repository.UserRepository;
import com.example.bermuda.repository.UserVoiceActorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecessionService {

    private final UserRepository userRepository;
    private final UserDiaryRepository userDiaryRepository;
    private final UserMusicRepository userMusicRepository;
    private final UserVoiceActorRepository userVoiceActorRepository;

    // 음악 리스트를 보여주는
    @Transactional
    public void secession(String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new);

        List<UserDiary> userDiarys = userDiaryRepository.findAllByUser(user).get();
        List<UserVoiceActor> userVoiceActors = userVoiceActorRepository.findAllByUser(user).get();
        List<UserMusic> userMusics = userMusicRepository.findAllByUser(user).get();

        for(UserDiary userDiary : userDiarys){
            User likedUser = userDiary.getUser();
            userDiary.setUser(null);
            likedUser.getStoredDiaryList().remove(userDiary);
            userDiary.setDiary(null);
        }

        for(UserVoiceActor userVoiceActor : userVoiceActors){
            User likedUser = userVoiceActor.getUser();
            userVoiceActor.setUser(null);
            likedUser.getStoredVoiceActorList().remove(userVoiceActor);
            userVoiceActor.setVoiceActor(null);
        }

        for(UserMusic userMusic : userMusics){
            User likedUser = userMusic.getUser();
            userMusic.setUser(null);
            likedUser.getStoredMusicList().remove(userMusic);
            userMusic.setMusic(null);
        }

        userRepository.deleteByUserId(userId).orElseThrow(CUserNotFoundException::new);
    }
}