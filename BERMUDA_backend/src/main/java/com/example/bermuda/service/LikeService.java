package com.example.bermuda.service;

import com.example.bermuda.domain.Diary;
import com.example.bermuda.domain.User;
import com.example.bermuda.domain.UserDiary;
import com.example.bermuda.exception.exceptions.CDiaryNotFoundException;
import com.example.bermuda.exception.exceptions.CUserNotFoundException;
import com.example.bermuda.repository.DiaryRepository;
import com.example.bermuda.repository.UserDiaryRepository;
import com.example.bermuda.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {

    final private UserDiaryRepository userDiaryRepository;
    final private DiaryRepository diaryRepository;
    final private UserRepository userRepository;

    @Transactional
    public void likeDiaryDto(Long diaryId) {

        // 현재 로그인된 유저
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new);

        Diary diary = diaryRepository.findByDiaryId(diaryId).orElseThrow(CDiaryNotFoundException::new);

        if(!userDiaryRepository.existsByUserAndDiary(user, diary)){
            // 좋아요!
            userDiaryRepository.save(
                    UserDiary.builder()
                            .diary(diary)
                            .user(user)
                            .build()
            );
        }
        else{
            // 좋아요 취소
            // 이거 예외처리 수정부탁드려용.
            userDiaryRepository.deleteByUserAndDiary(user, diary).orElseThrow(CDiaryNotFoundException::new);
        }
    }
}
