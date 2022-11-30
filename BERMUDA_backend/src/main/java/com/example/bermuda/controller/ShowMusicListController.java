package com.example.bermuda.controller;

import com.example.bermuda.dto.DiaryDto;
import com.example.bermuda.dto.MusicDto;
import com.example.bermuda.model.response.SingleResult;
import com.example.bermuda.service.DiaryListService;
import com.example.bermuda.service.MusicListService;
import com.example.bermuda.service.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/music-list")
public class ShowMusicListController {

    private final MusicListService musicListService;

    private final ResponseService responseService;

    @GetMapping("/show")
    @Operation(summary = "음악 리스트", description = "음악 리스트의 정보를 가져옴.")
    public SingleResult showMusicList(@RequestParam String mood, @PageableDefault(size = 10) Pageable pageable) {

        // 사용자 인증 후 꺼내오기(pagable이 페이지 단위로 불러오기는 효과적)
        List<MusicDto.PreviewMusicDto> musicDtoList = musicListService.getMusicList(mood, pageable);

        return responseService.getSingleResult(musicDtoList);
    }



}
