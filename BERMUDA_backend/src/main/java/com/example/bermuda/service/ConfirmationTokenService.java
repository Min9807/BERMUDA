package com.example.bermuda.service;


import com.example.bermuda.domain.ConfirmationToken;
import com.example.bermuda.exception.exceptions.CEmailAuthTokenNotFoundException;
import com.example.bermuda.repository.ConfirmationTokenRepository;
import io.jsonwebtoken.lang.Assert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailService;

    /**
     * 이메일 인증 토큰 생성
     * @return
     */
    public void createEmailConfirmationToken(String userId, String receiverEmail){
        log.info("userId : {}" , userId);
        log.info("email : {}" , receiverEmail);

        Assert.hasText(userId,"userId는 필수 입니다.");
        Assert.hasText(receiverEmail,"receiverEmail은 필수 입니다.");

        emailService.pageAuth(receiverEmail, userId, "http://localhost:8081/api/confirm-email?token=");
    }

    /**
     * 유효한 토큰 가져오기
     * @param confirmationTokenId
     * @return
     */
    public ConfirmationToken findByIdAndExpirationDateAfterAndExpired(String confirmationTokenId){
        return confirmationTokenRepository.findByIdAndExpirationDateAfterAndExpired(confirmationTokenId, LocalDateTime.now(),false).orElseThrow((CEmailAuthTokenNotFoundException::new));
    };

}