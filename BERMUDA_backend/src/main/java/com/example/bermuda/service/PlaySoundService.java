package com.example.bermuda.service;

import com.example.bermuda.domain.Music;
import com.example.bermuda.exception.exceptions.CMusicNotFoundException;
import com.example.bermuda.repository.MusicRepository;
import com.example.bermuda.utils.BasicInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlaySoundService {

    private final MusicRepository musicRepository;

    public ResponseEntity playSound(Long musicId) throws IOException {
        Music music = musicRepository.findByMusicId(musicId).orElseThrow(CMusicNotFoundException::new);
        String fileName = music.getStoredFilePath();
        File file = new File(fileName);
        long length = file.length();


        InputStreamResource inputStreamResource = new InputStreamResource( new FileInputStream(fileName));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentLength(length);
        httpHeaders.setCacheControl(CacheControl.noCache().getHeaderValue());
        httpHeaders.add("Content-Type", Files.probeContentType(Paths.get(fileName)));
        return new ResponseEntity(inputStreamResource, httpHeaders, HttpStatus.OK);
    }
}
