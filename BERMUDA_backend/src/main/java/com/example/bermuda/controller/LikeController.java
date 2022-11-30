package com.example.bermuda.controller;

import com.example.bermuda.dto.DiaryDto;
import com.example.bermuda.exception.exceptions.CWrongDiaryIdException;
import com.example.bermuda.model.response.CommonResult;
import com.example.bermuda.model.response.SingleResult;
import com.example.bermuda.service.LikeService;
import com.example.bermuda.service.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "like module", description = "좋아요 기능 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class LikeController {

    final private LikeService likeService;
    final private ResponseService responseService;

    @PutMapping("/diary/{diary-id}")
    @Operation(summary = "일기 좋아요", description = "일기 좋아요를 누른다.")
    public CommonResult updateLikeDiary(@PathVariable("diary-id") Long diaryId) {

        likeService.likeDiaryDto(diaryId);
        return responseService.getSuccessResult();

    }
}
