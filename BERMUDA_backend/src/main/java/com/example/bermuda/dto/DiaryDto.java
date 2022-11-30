package com.example.bermuda.dto;

import com.example.bermuda.domain.User;
import com.example.bermuda.domain.UserDiary;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

public class DiaryDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class RepDiaryDto {
        private Long diaryId;
        private User user;

        private String content;
        private Boolean open;
        private Integer likeNum;
        private LocalDate writeDate;

        private LocationDto.RepLocationDto repLocationDto;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateDiaryDto {
        // private String title;
        private String content;
        private Boolean open;

        private String theme;

        private LocationDto.RepLocationDto repLocationDto;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class DiaryPreviewDto {

        private Long diaryId;
        private String userId;
        private String loginId;
        private String nickName;
        // private String title;
        private String content;
        private Boolean open;
        //private LocalDate writeDate;
        private Boolean liked;

        private Long musicId;
        private String storedFilePath;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class DiaryResultDto {
        private Long diaryId;

        private String userId;

        // private String title;
        private String content;
        private Boolean open;
        private Integer likeNum;
        private LocalDate writeDate;

        private LocationDto.RepLocationDto repLocationDto;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ShowDiaryDto {
        private Long diaryId;

        private String userId;
        // private String title;
        private String content;
        private Boolean open;
        private LocalDate writeDate;

        private Boolean isMe;

        private Integer likeNum;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateDiaryDto {
        private String content;
        private Boolean open;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SendContentDto {
        private String content;
    }

}
