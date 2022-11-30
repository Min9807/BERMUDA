package com.example.bermuda.exception;

import com.example.bermuda.dto.StatusDto;
import com.example.bermuda.exception.exceptions.*;
import com.example.bermuda.model.response.CommonResult;
import com.example.bermuda.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlers {
    private final ResponseService responseService; // 결과에 대한 정보를 도출하는 클래스 명시

    @ExceptionHandler(CWrongDiaryIdException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult wrongDiaryIdException(HttpServletRequest request, CWrongDiaryIdException e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult(0, "Wrong diaryId");
    }

    @ExceptionHandler(CUserNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult userNotFoundException(HttpServletRequest request, CUserNotFoundException e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult(1, "user not exist");
    }


    @ExceptionHandler(CDiaryNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult diaryNotFoundException(HttpServletRequest request, CDiaryNotFoundException e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult(2, "diary not exist");
    }

    @ExceptionHandler(CVoicActorNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult voiceActorNotFoundException(HttpServletRequest request, CVoicActorNotFoundException e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult(3, "voice actor is not exist");
    }

    @ExceptionHandler(CMusicNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult musicNotFoundException(HttpServletRequest request, CMusicNotFoundException e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult(4, "voice actor is not exist");
    }

    @ExceptionHandler(CDiaryListNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult diaryListNotFoundException(HttpServletRequest request, CDiaryListNotFoundException e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult(5, "diary list not exist");
    }

    @ExceptionHandler(CNotAuthorizedUserException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult notAuthorizedUserException(HttpServletRequest request, CNotAuthorizedUserException e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult(6, "not authorized user");
    }

    @ExceptionHandler(CAuthenticationException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult authenticationException(HttpServletRequest request, CAuthenticationException e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult(7, "illegal authentication");
    }

    @ExceptionHandler(CStringFormatException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult stringFormatException(HttpServletRequest request, CStringFormatException e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult(8, "used string type format");
    }

    @ExceptionHandler(CUserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult userAlreadyExistsException(HttpServletRequest request, CUserAlreadyExistsException e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult(9, "user is already exist");
    }

    @ExceptionHandler(CUserIdAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult userIdAlreadyExistsException(HttpServletRequest request, CUserIdAlreadyExistsException e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult(10, "userid is already exist");
    }

    @ExceptionHandler(CNickNameAlreadyExistsException.class)
    protected CommonResult nickNameAlreadyExistsException(HttpServletRequest request, CNickNameAlreadyExistsException e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult(11, "user's nickname is already exist");
    }

    @ExceptionHandler(CEmailAuthTokenNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult emailAuthTokenNotFoundException(HttpServletRequest request, CEmailAuthTokenNotFoundException e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult(12, "email authentication token is not found");
    }

    @ExceptionHandler(CSigninFailedException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult signinFailedException(HttpServletRequest request, CSigninFailedException e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult(13, "sigin in failed");
    }

    @ExceptionHandler(CExtensionException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult extensionException(HttpServletRequest request, CExtensionException e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult(14, "illegal file extension");
    }

    @ExceptionHandler(CFileNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult fileNotFoundException(HttpServletRequest request, CFileNotFoundException e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult(15, "file not found");
    }

    @ExceptionHandler(CProfileImageNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult profileImageNotFoundException(HttpServletRequest request, CProfileImageNotFoundException e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult(16, "profileImage not exist");
    }

    @ExceptionHandler(CCurPasswordFailedException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult curPasswordFailedException(HttpServletRequest request, CCurPasswordFailedException e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult(17, "current password is wrong");
    }

    @ExceptionHandler(CUserDiaryNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult userDiaryNotFoundException(HttpServletRequest request, CUserDiaryNotFoundException e) {
        // CommonResult : 응답 결과에 대한 정보
        return responseService.getFailResult(17, "current password is wrong");
    }
}
