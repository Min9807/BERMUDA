package com.example.bermuda.converter;

import com.example.bermuda.domain.ProfileImage;
import com.example.bermuda.dto.ProfileImageDto;

public class ProfileImageDtoConverter {
    public static ProfileImageDto.RepProfileImageDto toRepProfileImageDto(ProfileImage profileImage) {
        return ProfileImageDto.RepProfileImageDto.builder()
                .originalFileName(profileImage.getOriginalFileName())
                .storedFilePath(profileImage.getStoredFilePath())
                .fileSize(profileImage.getFileSize())
                .userId(profileImage.getUser().getUserId())
                .build();
    }
}
