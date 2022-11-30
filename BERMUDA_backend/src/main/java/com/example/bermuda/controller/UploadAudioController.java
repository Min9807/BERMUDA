package com.example.bermuda.controller;

import com.example.bermuda.dto.StatusDto;
import com.example.bermuda.dto.UserDto;
import com.example.bermuda.model.response.CommonResult;
import com.example.bermuda.service.ResponseService;
import com.example.bermuda.service.UploadService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Tag(name = "upload audio", description = "음원 업로드 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/upload")
public class UploadAudioController {

    private final UploadService uploadService;
    private final ResponseService responseService;

    @ApiOperation(value = "음악 업로드", notes = "음악 파일을 업로드한다.")
    @PostMapping(value = "/music")
    public CommonResult uploadMusic(@RequestParam(value="file") MultipartFile multipartFile, @RequestParam String songName, @RequestParam String mood) throws IOException {
        String loginId = SecurityContextHolder.getContext().getAuthentication().getName();
        uploadService.uploadMusicByLoginId(loginId, multipartFile, songName, mood);
        return responseService.getSuccessResult();
    }
}
