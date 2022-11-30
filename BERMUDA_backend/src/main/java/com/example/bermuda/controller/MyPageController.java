package com.example.bermuda.controller;

import com.example.bermuda.domain.User;
import com.example.bermuda.exception.exceptions.CUserNotFoundException;
import com.example.bermuda.model.response.CommonResult;
import com.example.bermuda.model.response.SingleResult;
import com.example.bermuda.repository.UserRepository;
import com.example.bermuda.service.ResponseService;
import com.example.bermuda.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "profile image controller", description = "프로필 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class MyPageController {
//    // UserService 만들었음.
//    private final UserService userService;
    private final ResponseService responseService;
    private final UserRepository userRepository;
//
//    // 사용자 프로필 화면으로 이동
//    @GetMapping("/{user-id}")
//    @Operation(summary = "나의 프로필", description = "내 프로필 정보를 가져옴.")
//    public byte[] showProfileImage(@PathVariable(value = "user-id") String userId) throws IOException {
//        //String loginId = SecurityContextHolder.getContext().getAuthentication().getName();   // user id 받아오는
//        return userService.getUserProfileImageDtoByUserId(userId);                                                                  // return이 이게 맞나?
//    }
//
//    // 사용자 프로필 화면으로 이동
//    @GetMapping(value = "/my-page")
//    @Operation(summary = "나의 프로필", description = "내 프로필 정보를 가져옴.")
//    public @ResponseBody byte[] showMyProfileImage() throws IOException {
//        String loginId = SecurityContextHolder.getContext().getAuthentication().getName();   // user id 받아오는
//        return userService.getUserProfileImageDtoByUserId(loginId);                                                                // return이 이게 맞나?
//    }
//
//    // 사용자 프로필 변경
//    // 프론트애들에게 multipartFile 알려주기
//    @PutMapping("/my-page")
//    @Operation(summary = "프로필 변경", description = "프로필을 변경함.")
//    public CommonResult editProfileImage(@RequestPart(name = "profileImage") MultipartFile profileImage) throws IOException {
//        log.info("log3");
//        String loginId = SecurityContextHolder.getContext().getAuthentication().getName();   // user id
//        log.info("log");
//        userService.updateProfileImageByLoginIdAndUserId(profileImage, loginId);
//        log.info("log2");
//        return responseService.getSuccessResult();
//    }
    @GetMapping("/mypage")
    @Operation(summary = "프로필 변경", description = "프로필을 변경함.")
    public SingleResult userNickname() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new);
        return responseService.getSingleResult(user.getNickName());
    }
}
