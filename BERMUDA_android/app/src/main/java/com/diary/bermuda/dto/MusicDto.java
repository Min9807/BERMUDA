package com.diary.bermuda.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MusicDto {
    public static class RepMusicDto {
        @SerializedName("musicId")
        private Long musicId;

//        private AtmosphereDrum atmosphereDrum;
//        private AtmosphereCode atmosphereCode;
//        private Background background;
//        private BPM bpm;

        @SerializedName("originalFileName")
        private String originalFileName;
        @SerializedName("storedFilePath")
        private String storedFilePath;

        @SerializedName("fileSize")
        private long fileSize;
        @SerializedName("mood")
        private String mood;
        //해당 음악의 작곡가
//        private User user;
//
//        //음악 구매자 리스트
//        private List<UserMusic> storedUserList;
    }

    public static class PreviewMusicDto {
        @SerializedName("musicId")
        private Long musicId;

        @SerializedName("originalFileName")
        private String originalFileName;

        //해당 음악의 작곡가
        @SerializedName("userNickname")
        private String userNickname;

        @SerializedName("storedFilePath")
        private String storedFilePath;
        public Long getMusicId() {
            return musicId;
        }

        public void setMusicId(Long musicId) {
            this.musicId = musicId;
        }

        public String getOriginalFileName() {
            return originalFileName;
        }

        public void setOriginalFileName(String originalFileName) {
            this.originalFileName = originalFileName;
        }

        public String getUserNickname() {
            return userNickname;
        }

        public void setUserNickname(String userNickname) {
            this.userNickname = userNickname;
        }

        public String getStoredFilePath() {
            return storedFilePath;
        }

        public void setStoredFilePath(String storedMusicFilePath) {
            this.storedFilePath = storedMusicFilePath;
        }
    }

    public static class StoredMusicDto {
        @SerializedName("musicId")
        private Long musicId;
        @SerializedName("mood")
        private String mood;
        @SerializedName("originalFileName")
        private String originalFileName;
        @SerializedName("storedFilePath")
        private String storedFilePath;

        @SerializedName("fileSize")
        private long fileSize;

        public Long getMusicId() {
            return musicId;
        }

        public void setMusicId(Long musicId) {
            this.musicId = musicId;
        }

        public String getMood() {
            return mood;
        }

        public void setMood(String mood) {
            this.mood = mood;
        }

        public String getOriginalFileName() {
            return originalFileName;
        }

        public void setOriginalFileName(String originalFileName) {
            this.originalFileName = originalFileName;
        }

        public String getStoredFilePath() {
            return storedFilePath;
        }

        public void setStoredFilePath(String storedFilePath) {
            this.storedFilePath = storedFilePath;
        }

        public long getFileSize() {
            return fileSize;
        }

        public void setFileSize(long fileSize) {
            this.fileSize = fileSize;
        }

    }
}
