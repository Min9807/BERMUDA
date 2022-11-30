package com.example.bermuda.dto;

import lombok.*;


public class ProfileImageDto {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RepProfileImageDto {
        private Long profileImageId;
        private String originalFileName;
        private String storedFilePath;
        private Long fileSize;

        private String userId;
    }
//    @Getter
//    @Builder
//    @AllArgsConstructor
//    @NoArgsConstructor
//    public static class ShowProfileImageDto {
//        private byte[] byteArray;
//    }
    public static class MypageNicknameDto {
        private String userId;
    }
}
