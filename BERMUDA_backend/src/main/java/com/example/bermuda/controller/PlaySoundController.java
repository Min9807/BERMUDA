package com.example.bermuda.controller;

import com.example.bermuda.service.PlaySoundService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;

@Tag(name = "음원 재생 API", description = "음원 재생")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PlaySoundController {

    private final PlaySoundService playSoundService;

    @GetMapping( "/music/{musicId}")
    public ResponseEntity playAudio(@PathVariable("musicId") String musicIds) throws IOException {
        Long musicId = Long.parseLong(musicIds);

        return playSoundService.playSound(musicId);
    }

}
