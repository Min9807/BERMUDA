package com.example.bermuda.controller;

import com.example.bermuda.dto.DiaryDto;
import com.example.bermuda.dto.GetAnalysisDto;
import com.example.bermuda.exception.exceptions.CAuthenticationException;
import com.example.bermuda.exception.exceptions.CWrongDiaryIdException;
import com.example.bermuda.model.response.CommonResult;
import com.example.bermuda.model.response.SingleResult;
import com.example.bermuda.service.DiaryService;
import com.example.bermuda.service.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "diary post", description = "일기 작업 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DiaryController {

    final private DiaryService diaryService;
    final private ResponseService responseService;

    @GetMapping("/diary/{diary-id}")
    @Operation(summary = "나의 일기장 리스트", description = "내가 작성한 일기 정보를 가져옴.")
    public SingleResult showDiary(@PathVariable("diary-id") String diaryIds) {
        Long diaryId;
        DiaryDto.ShowDiaryDto diaryDto;

        log.info(diaryIds + "");

        try {
            diaryId = Long.parseLong(diaryIds);
            //일기장을 showdto로 보여지는 용도로만 사용
            diaryDto = diaryService.getDiaryByDiaryId(diaryId);
        } catch (CWrongDiaryIdException e) {
            throw new CWrongDiaryIdException();
        }
        return responseService.getSingleResult(diaryDto);
    }

    @PostMapping("/diary")
    @Operation(summary = "일기 등록", description = "제목(title)과 내용(content)을 이용하여 일기를 신규 등록합니다.")
    public CommonResult createDiary(@RequestBody DiaryDto.CreateDiaryDto createDiaryDto) {
        //권한을 통해 유저 id를 획득합니다.
        String userId;

        try {
            //권한을 통해 유저 id를 획득합니다.
            userId = SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (NullPointerException e) {
            log.info("Not legal authentication.");
            throw new CAuthenticationException();
        }

        diaryService.saveDiary(userId, createDiaryDto);

        return responseService.getSuccessResult();
    }

    @PutMapping("/diary/{diary-id}")
    @Operation(summary = "일기 수정", description = "일기의 내용을 수정합니다. 제목 수정은 안됩니다.")
    public SingleResult updateDiary(@RequestBody DiaryDto.UpdateDiaryDto updateDiaryDto, @PathVariable("diary-id") String diaryIds) {
        Long diaryId;
        DiaryDto.DiaryResultDto diaryResultDto;

        log.info(diaryIds + "");

        try {
            diaryId = Long.parseLong(diaryIds);
            //일기장을 showdto로 보여지는 용도로만 사용
            diaryResultDto = diaryService.updateContentAndOpenByDiaryId(updateDiaryDto, diaryId);
        } catch (CWrongDiaryIdException e) {
            throw new CWrongDiaryIdException();
        }
        return responseService.getSingleResult(diaryResultDto);
    }

    @DeleteMapping("/diary/{diary-id}")
    @Operation(summary = "일기 삭제", description = "일기를 삭제합니다.")
    public SingleResult deleteDiary(@PathVariable("diary-id") Long diaryId) {
        return responseService.getSingleResult(diaryService.deleteByDiaryId(diaryId));
    }

    @PostMapping("/diary/like")
    @Operation(summary = "일기 좋아요", description = "일기를 좋아요/좋아요 취소 입력.")
    public CommonResult updateLike(@RequestParam(value = "like")Boolean liked, @RequestParam(value = "diaryId") String diaryIds){
        String loginId = SecurityContextHolder.getContext().getAuthentication().getName();
        Long diaryId = Long.parseLong(diaryIds);

        log.info(liked+"");

        diaryService.updateLike(loginId, diaryId, liked);

        return responseService.getSuccessResult();
    }
}