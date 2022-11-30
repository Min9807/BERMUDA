package com.example.bermuda.service;

import com.example.bermuda.domain.User;
import com.example.bermuda.exception.exceptions.CCurPasswordFailedException;
import com.example.bermuda.exception.exceptions.CSigninFailedException;
import com.example.bermuda.exception.exceptions.CUserNotFoundException;
import com.example.bermuda.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateAccountsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화

    @Transactional
    public void UpdatePwDto(String curPw, String newPw){
        log.info("여기인가");
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        log.info("id : {}" , userId);
        log.info("password : {}" , curPw);

        User user = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new);

        if (!passwordEncoder.matches(curPw, user.getPassword())) {
            // matches : 평문, 암호문 패스워드 비교 후 boolean 결과 return
            throw new CCurPasswordFailedException();
        }

        user.setUserPw(passwordEncoder.encode(newPw));

    }

}
