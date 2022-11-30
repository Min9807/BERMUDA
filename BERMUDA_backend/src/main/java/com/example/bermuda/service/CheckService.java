package com.example.bermuda.service;

import com.example.bermuda.dto.UserDto;
import com.example.bermuda.exception.exceptions.CNickNameAlreadyExistsException;
import com.example.bermuda.exception.exceptions.CUserAlreadyExistsException;
import com.example.bermuda.exception.exceptions.CUserIdAlreadyExistsException;
import com.example.bermuda.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CheckService {

    private final UserRepository userRepository;

    @Transactional
    public void check(UserDto.SignUpUserDto signUpUserDto){
        checkUserNameAndEmail(signUpUserDto.getUserName(), signUpUserDto.getEmail());
        checkId(signUpUserDto.getUserId());
        checkNickName(signUpUserDto.getNickName());
    }

    @Transactional
    public void checkId(String userId) {
        //아이디가 존재하면 생성 불가
        if (userRepository.existsByUserId(userId))
            throw new CUserIdAlreadyExistsException();
    }

    //이메일이 존재하는지 확인정도의 서비스
    @Transactional
    public void checkUserNameAndEmail(String userName, String email) {
        if (userRepository.existsByUserNameAndEmail(userName, email))
            throw new CUserAlreadyExistsException();
    }

    @Transactional
    public Boolean checkUserIdAndEmail(String userId, String email) {
        if (userRepository.existsByUserIdAndEmail(userId, email)) {
            return false;
        }
        return true;
    }

    @Transactional
    public void checkNickName(String nickName) {
        //아이디가 존재하면 생성 불가
        if (userRepository.existsByNickName(nickName))
            throw new CNickNameAlreadyExistsException();
    }
}
