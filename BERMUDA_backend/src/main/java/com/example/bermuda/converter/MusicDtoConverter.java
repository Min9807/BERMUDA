package com.example.bermuda.converter;

import com.example.bermuda.domain.Diary;
import com.example.bermuda.domain.Music;
import com.example.bermuda.domain.VoiceActor;
import com.example.bermuda.dto.DiaryDto;
import com.example.bermuda.dto.MusicDto;
import com.example.bermuda.dto.VoiceActorDto;

public class MusicDtoConverter {
    public static MusicDto.PreviewMusicDto toShowMusicDto(Music music) {
        return MusicDto.PreviewMusicDto.builder()
                .musicId(music.getMusicId())
                .originalFileName(music.getOriginalFileName())
                .storedFilePath(music.getStoredFilePath())
                .userNickname(music.getUser().getNickName())
                .build();
    }
    public static MusicDto.StoredMusicDto toStoredMusic(Music music) {
        return MusicDto.StoredMusicDto.builder()
                .atmosphereCode(music.getAtmosphereCode())
                .atmosphereDrum(music.getAtmosphereDrum())
                .bpm(music.getBpm())
                .background(music.getBackground())
                .fileSize(music.getFileSize())
                .storedFilePath(music.getStoredFilePath())
                .originalFileName(music.getOriginalFileName())
                .user(music.getUser())
                .build();
    }
}
