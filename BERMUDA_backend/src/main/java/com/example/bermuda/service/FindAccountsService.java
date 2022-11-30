package com.example.bermuda.service;

import com.example.bermuda.domain.User;
import com.example.bermuda.dto.UserDto;
import com.example.bermuda.exception.exceptions.CEmailAuthTokenNotFoundException;
import com.example.bermuda.exception.exceptions.CSigninFailedException;
import com.example.bermuda.exception.exceptions.CUserNotFoundException;
import com.example.bermuda.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindAccountsService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String findIdDto(String name, String email){
        //입력받은 이름과 이메일

        log.info("name : {}" , name);
        log.info("email : {}" , email);

        User user = userRepository.findByUserNameAndEmail(name, email).orElseThrow(CUserNotFoundException::new);
        return user.getUserId();
    }

    @Transactional
    public void findPwDto(String userId, String email, String username){
        //입력받은 아이디와 이메일

        log.info("id : {}" , userId);
        log.info("email : {}" , email);

        User user = userRepository.findByUserIdAndEmailAndUserName(userId, email, username).orElseThrow(CUserNotFoundException::new);

        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '!', '@', '#', '$', '^' };

        StringBuffer sb = new StringBuffer();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());

        int idx = 0;
        int len = charSet.length;
        for (int i=0; i<12; i++) {
            // idx = (int) (len * Math.random());
            idx = sr.nextInt(len);    // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
            sb.append(charSet[idx]);
        }

        user.setUserPw(passwordEncoder.encode(sb.toString()));

        emailService.changeTempPw(user.getEmail(), sb.toString());
    }
}
