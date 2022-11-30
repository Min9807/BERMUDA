package com.example.bermuda.converter;


import com.example.bermuda.domain.User;
import com.example.bermuda.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {
    public static UserDto.UserProfileDto toUserProfileDto(User user){
        return UserDto.UserProfileDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .userName(user.getUserName())
                .nickName(user.getNickName())
                .introduction(user.getIntroduction())
//                .profileImage(user.getProfileImage())
                .followingNum(user.getFollowingNum())
                .followerNum(user.getFollowerNum())
                .musicProvider(user.getMusicProvider())
                .subscription(user.getSubscription())
                .myDiaryList(user.getMyDiaryList())
                .myMusicList(user.getMyMusicList())
                .loginUser(true)
                .build();
    }
}
