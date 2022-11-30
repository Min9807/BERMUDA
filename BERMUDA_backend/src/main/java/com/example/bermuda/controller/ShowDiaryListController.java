package com.example.bermuda.controller;

import com.example.bermuda.dto.DiaryDto;
import com.example.bermuda.dto.LocationDto;
import com.example.bermuda.model.response.ListResult;
import com.example.bermuda.model.response.SingleResult;
import com.example.bermuda.service.DiaryListService;
import com.example.bermuda.service.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.List;

@Tag(name = "get diary list", description = "일기 리스트 출력 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diary-list")
public class ShowDiaryListController {

    private final DiaryListService diaryListService;
    private final ResponseService responseService;

    @GetMapping("/mine")
    @Operation(summary = "나의 일기장 리스트", description = "내가 작성한 일기 리스트의 정보를 가져옴.")
    public SingleResult showMyDiaryList(@PageableDefault(size = 10) Pageable pageable) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        // 사용자 인증 후 꺼내오기(pagable이 페이지 단위로 불러오기는 효과적)
        List<DiaryDto.DiaryPreviewDto> diaryPreviewDtoList = diaryListService.getMyDiaryListByUserId(userId, pageable);

        return responseService.getSingleResult(diaryPreviewDtoList);
    }

    // 위치에 따른 값 얻는걸 쿼리로 짜는 법을 공부해야함.
    @GetMapping("/neighbor")
    @Operation(summary = "주변 일기장 리스트", description = "위치 기반의 나의 위치 근처 일기 리스트 정보를 가져옴.")
    public SingleResult showNeighborDiaryList(@RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude) throws IOException {
        String userId;
        try {
            userId = SecurityContextHolder.getContext().getAuthentication().getName();
        }catch(NullPointerException e){
            userId = null;
        }
        List<DiaryDto.DiaryPreviewDto> diaryPreviewDtoList = diaryListService.getNearDiaryByLocationDto(latitude,longitude, userId);

        return responseService.getSingleResult(diaryPreviewDtoList);
    }
}
