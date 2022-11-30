package com.example.bermuda.service;

import com.example.bermuda.domain.ProfileImage;
import com.example.bermuda.domain.User;
import com.example.bermuda.dto.UserDto;
import com.example.bermuda.repository.ProfileImageRepository;
import com.example.bermuda.repository.UserRepository;
import com.example.bermuda.utils.BasicInfo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final ProfileImageRepository profileImageRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화

    @Transactional
    public void joinUser(UserDto.SignUpUserDto signUpUserDto) {
        User user = User.builder()
                .userId(signUpUserDto.getUserId())
                .userPw(passwordEncoder.encode(signUpUserDto.getUserPw()))
                .userName(signUpUserDto.getUserName())
                .email(signUpUserDto.getEmail())
                .nickName(signUpUserDto.getNickName())
                .followerNum(0)
                .followingNum(0)
                .musicProvider(false)
                .emailAuth(true)
                .subscription(false)
                .roles(Collections.singletonList("ROLE_USER"))
                .build();


        userRepository.save(user);
    }
}
