package com.example.bermuda.controller;

import com.example.bermuda.dto.DiaryDto;
import com.example.bermuda.dto.VoiceActorDto;
import com.example.bermuda.model.response.ListResult;
import com.example.bermuda.model.response.SingleResult;
import com.example.bermuda.service.ResponseService;
import com.example.bermuda.service.StoredDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Tag(name = "stored controller", description = "저장한 자료 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/liked")
public class ShowStoredListController {

    private final StoredDataService storedDataService;
    private final ResponseService responseService;

    @GetMapping("/diary")
    @Operation(summary = "저장한 일기", description = "좋아요 해둔 일기장을 가져옴.")
    public SingleResult showStoredDiary() throws IOException {
        String loginId = SecurityContextHolder.getContext().getAuthentication().getName();
        List<DiaryDto.DiaryPreviewDto> diaryPreviewDtoList = storedDataService.getStoredDiaryDtoListByLoginId(loginId);
        return responseService.getSingleResult(diaryPreviewDtoList);
    }

    @GetMapping("/voice-actor")
    @Operation(summary = "저장한 성우 목소리", description = "저장한 성우 음성을 가져옴.")
    public SingleResult showStoredVoiceActor(){
        String loginId = SecurityContextHolder.getContext().getAuthentication().getName();
        List<VoiceActorDto.StoredVoiceActorDto> storedVoiceActorDtoList = storedDataService.getStoredVoiceActorDtoListByLoginId(loginId);
        return responseService.getSingleResult(storedVoiceActorDtoList);
    }
//
//    @GetMapping("/music")
//    @Operation(summary = "저장한 음악", description = "저장한 음악을 가져옴.")
//    public ListResult showStoredMusic(){
//        String loginId = SecurityContextHolder.getContext().getAuthentication().getName();
//        List<MusicDto.StoredMusicDto> storedMusicDtoList = storedListService.getStoredMusicDtoListByLoginId(loginId);
//        return responseService.getListResult(storedMusicDtoList);
//    }
}
