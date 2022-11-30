package com.example.bermuda.dto;

import com.example.bermuda.domain.Diary;
import com.example.bermuda.domain.Music;
import com.example.bermuda.domain.ProfileImage;
import lombok.*;

import java.util.List;


public class UserDto {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Data
    public static class UserProfileDto {
        private String userId;
        private String email;
        private String userName;
        private String nickName;
        private String introduction;

        private ProfileImage profileImage;

        private Integer followingNum;
        private Integer followerNum;

        private Boolean musicProvider;

        private Boolean subscription;

        private List<Diary> myDiaryList;
        private List<Music> myMusicList;

        private boolean loginUser;
    }

    @Getter
    public static class SignUpUserDto {

        private String userId;
        private String userPw;
        private String email;
        private String userName;
        private String nickName;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SubscriptionDto {

        private Boolean subscription;

    }

    @Getter
    public static class FindPwDto {

        private String userId;
        private String email;
        private String userName;

    }
    @Getter
    public static class ChangePwDto {

        private String curPw;
        private String newPw;

    }
}