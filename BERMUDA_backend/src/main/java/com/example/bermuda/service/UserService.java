package com.example.bermuda.service;

import com.example.bermuda.converter.ProfileImageDtoConverter;
import com.example.bermuda.domain.ProfileImage;
import com.example.bermuda.domain.User;
import com.example.bermuda.dto.ProfileImageDto;
import com.example.bermuda.exception.exceptions.CUserNotFoundException;
import com.example.bermuda.repository.ProfileImageRepository;
import com.example.bermuda.repository.UserRepository;
import com.example.bermuda.utils.FileHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
//
//@RequiredArgsConstructor
//@Service
//@Slf4j
//public class UserService {
//
//    private final UserRepository userRepository;
//    private final ConfirmationTokenService confirmationTokenService;
//    private final ResponseService responseService;
//
//    /**
//     * 이메일 인증 로직
//     * @param token
//     */
//    @Transactional
//    public void confirmEmail(String token) {
//        ConfirmationToken findConfirmationToken = confirmationTokenService.findByIdAndExpirationDateAfterAndExpired(token);
//        User user = userRepository.findByUserId(findConfirmationToken.getUserId()).orElseThrow();
//        findConfirmationToken.useToken();	// 토큰 만료 로직을 구현해주면 된다. ex) expired 값을 true로 변경
//        user.emailVerifiedSuccess();	// 유저의 이메일 인증 값 변경 로직을 구현해주면 된다. ex) emailVerified 값을 true로 변경
//    }
//
//    @Transactional
//    public SingleResult deleteUser(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String loginId = authentication.getName();
//        System.out.println(loginId);
//        //이미 로그인 되어있는 상태이기 때문에 굳이 유저 확인할 필요는 없을 듯
//        if(userRepository.existsByUserId(loginId)) {
//            userRepository.deleteByUserId(loginId);
//            return responseService.getSingleResult("deleted complete");
//        }
//        else
//            return responseService.getSingleResult("deleted failed");
//    }
//}

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
//    private final UserRepository userRepository;
//    private final ProfileImageRepository profileImageRepository;
//    private final FileHandler fileHandler;
//
//    @Transactional
//    public byte[] getUserProfileImageDtoByUserId(String userId) throws IOException {
//        // currentId가 로그인된
//        log.info(userId);
//        User loginUser = userRepository.findByUserId(userId).orElseThrow(CUserNotFoundException::new);
//
//        ProfileImage profileImage = profileImageRepository.findByUser(loginUser).orElseThrow();
//
//        log.info("test2");
//
//        ProfileImageDto.RepProfileImageDto repProfileImageDto = ProfileImageDtoConverter.toRepProfileImageDto(profileImage);
//
//        log.info("test3");
//
//        log.info(repProfileImageDto.getStoredFilePath());
//
//        return fileHandler.getProfileImageByte(repProfileImageDto);
//    }
//
//    // 유저의 프로필 변경하는 곳
//    @Transactional
//    public void updateProfileImageByLoginIdAndUserId(MultipartFile profileImageFile, String loginId) throws IOException {
//        ProfileImageDto.RepProfileImageDto repProfileImageDto;
//
//        // currentId가 로그인된 사용자 인지 확인
//        User loginUser = userRepository.findByUserId(loginId).orElseThrow(CUserNotFoundException::new);
//        log.info(loginUser.getUserId());
//        //ProfileImageChangeDto profileImageChangeDto = profileImageManage.uploadImage(loginUser, profileImageFile);
//
//        //프로필 사진이 존재하는 경우 사진 정보만 교체
//        if(profileImageRepository.existsByUser(loginUser)){
//            ProfileImage profileImage = profileImageRepository.findByUser(loginUser).orElseThrow(CUserNotFoundException::new);
//            repProfileImageDto = fileHandler.uploadImage(loginUser, profileImageFile);
//            profileImage.updateProfileImage(
//                    repProfileImageDto.getOriginalFileName(),
//                    repProfileImageDto.getStoredFilePath(),
//                    repProfileImageDto.getFileSize()
//            );
//        }else{
//            //프로필 사진이 존재하지 않는 경우 사진도 추가하고 관계도 형성
//            repProfileImageDto = fileHandler.uploadImage(loginUser, profileImageFile);
//            ProfileImage profileImage = ProfileImage.builder()
//                    .originalFileName(repProfileImageDto.getOriginalFileName())
//                    .storedFilePath(repProfileImageDto.getStoredFilePath())
//                    .fileSize(repProfileImageDto.getFileSize())
//                    .user(loginUser)
//                    .build();
//            profileImageRepository.save(profileImage);
//            loginUser.setProfileImage(profileImage);
//        }
//    }
}
