package com.example.bermuda.dto;

import com.example.bermuda.domain.User;
import com.example.bermuda.domain.UserMusic;
import com.example.bermuda.domain.music_type.AtmosphereCode;
import com.example.bermuda.domain.music_type.AtmosphereDrum;
import com.example.bermuda.domain.music_type.BPM;
import com.example.bermuda.domain.music_type.Background;
import lombok.*;

import java.util.List;

public class MusicDto {
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class RepMusicDto {
        private Long musicId;

        private AtmosphereDrum atmosphereDrum;
        private AtmosphereCode atmosphereCode;
        private Background background;
//        private BPM bpm;

        private String originalFileName;
        private String storedFilePath;

        private long fileSize;
        private String mood;
        //해당 음악의 작곡가
        private User user;

        //음악 구매자 리스트
        private List<UserMusic> storedUserList;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PreviewMusicDto {
        private Long musicId;

        private String originalFileName;
        private String storedFilePath;

        private long fileSize;

        //해당 음악의 작곡가
        private String userNickname;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class StoredMusicDto {
        private Long musicId;

        private AtmosphereDrum atmosphereDrum;
        private AtmosphereCode atmosphereCode;
        private Background background;
        private BPM bpm;
        private String mood;
        private String originalFileName;
        private String storedFilePath;

        private long fileSize;
        //해당 음악의 작곡가
        private User user;
    }
}
