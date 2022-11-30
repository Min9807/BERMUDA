package com.example.bermuda.controller;

import com.example.bermuda.converter.StatusDtoConverter;
import com.example.bermuda.dto.UserDto;
import com.example.bermuda.model.response.CommonResult;
import com.example.bermuda.service.CheckService;
import com.example.bermuda.service.ConfirmationTokenService;
import com.example.bermuda.service.ResponseService;
import com.example.bermuda.service.SignUpService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "normal signup", description = "일반 회원 가입 api")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SignUpController {

    private final SignUpService signUpService;
    private final ConfirmationTokenService confirmationTokenService;
    private final CheckService checkService;
    private final ResponseService responseService;


    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping(value = "/signup")
    public CommonResult signup(@RequestBody UserDto.SignUpUserDto signUpUserDto){
        log.info("email : {} " , signUpUserDto.getEmail());
        log.info("userId : {} " , signUpUserDto.getUserId());
        log.info("userName : {} " , signUpUserDto.getUserName());
        log.info("nickname : {} " , signUpUserDto.getNickName());
        log.info("password : {} " , signUpUserDto.getUserPw());
        //아이디, 닉네임, 이메일 중복 확인
        checkService.check(signUpUserDto);
        //confirmationTokenService.createEmailConfirmationToken(signUpUserDto.getUserId(), signUpUserDto.getEmail());
        signUpService.joinUser(signUpUserDto);

        return responseService.getSuccessResult();
    }

    //success값이 true 일 때만!
    @ApiOperation(value = "id중복체크")
    @GetMapping("/check-id/{userId}")
    public CommonResult checkId(@PathVariable String userId){
        log.info("checkId : {} ",userId);
        checkService.checkId(userId);
        return responseService.getSuccessResult();
    }

    //success값이 true 일 때만!
    @ApiOperation(value = "닉네임 중복체크")
    @GetMapping(value = "/check-nickname/{nickName}")
    public CommonResult checkNickName(@PathVariable String nickName){
        checkService.checkNickName(nickName);
        return responseService.getSuccessResult();
    }
}
