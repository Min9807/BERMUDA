package com.example.bermuda.controller;

import com.example.bermuda.config.security.JwtTokenProvider;
import com.example.bermuda.dto.StatusDto;
import com.example.bermuda.dto.UserDto;
import com.example.bermuda.exception.exceptions.CUserNotFoundException;
import com.example.bermuda.model.response.CommonResult;
import com.example.bermuda.service.LogInService;
import com.example.bermuda.service.ResponseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Tag(name = "normal signin", description = "일반 로그인 api")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SignInController {

    private final LogInService logInService;
    private final ResponseService responseService;
    private final JwtTokenProvider jwtTokenProvider;

     @ApiOperation(value = "자동 로그인", notes = "자동 로그인을 한다.")
     @GetMapping(value = "/auto-login")
     public CommonResult autoLogin(HttpServletRequest request) {
     if ((jwtTokenProvider.resolveAccessToken(request) == null)
     && (jwtTokenProvider.resolveRefreshToken(request) == null))
     throw new CUserNotFoundException();
     else if
     (!jwtTokenProvider.validateToken(jwtTokenProvider.resolveRefreshToken(request)))
     throw new CUserNotFoundException();
     return responseService.getSuccessResult();

     }

    @ApiOperation(value = "로그인", notes = "회원 로그인을 한다.")
    @GetMapping(value = "/signin")
    public CommonResult signin(@RequestParam(value = "id") String id, @RequestParam(value = "password") String password, HttpServletResponse response) {
        log.info("여긴가요?");
        logInService.signInByLoginDto(id, password, response);
        return responseService.getSuccessResult();
    }
}
