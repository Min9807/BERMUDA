package com.example.bermuda.service;

import com.example.bermuda.converter.MusicDtoConverter;
import com.example.bermuda.domain.Music;
import com.example.bermuda.dto.MusicDto;
import com.example.bermuda.exception.exceptions.CMusicNotFoundException;
import com.example.bermuda.repository.MusicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MusicListService {

    private final MusicRepository musicRepository;

    public MusicListService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }


    // 음악 리스트를 보여주는
    @Transactional
    public List<MusicDto.PreviewMusicDto> getMusicList(String mood, Pageable pageable) {
        Page<Music> musics;
        
        if(mood.equals("all")){
            musics = musicRepository.findAll(pageable);

        }
        else{
            musics = musicRepository.findByMood(mood, pageable);
        }

        List<MusicDto.PreviewMusicDto> musicDtoList = new ArrayList<>();
        // dto리스트로 변환
        for (Music music : musics) {
            musicDtoList.add(MusicDtoConverter.toShowMusicDto(music));
        }

        return musicDtoList;
    }

}
