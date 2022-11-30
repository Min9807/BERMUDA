package com.example.bermuda.converter;

import com.example.bermuda.domain.Diary;
import com.example.bermuda.dto.DiaryDto;
import org.springframework.stereotype.Component;

@Component
public class DiaryDtoConverter {
    public static DiaryDto.ShowDiaryDto toShowDiaryDto(Diary diary, Boolean isMe) {
        return DiaryDto.ShowDiaryDto.builder()
                .diaryId(diary.getDiaryId())
                .userId(diary.getUser().getUserId())
                .content(diary.getContent())
                .open(diary.getOpen())
                .likeNum(diary.getLikeNum())
                .writeDate(diary.getWriteDate())
                .isMe(isMe)
                .build();
    }

    public static DiaryDto.DiaryPreviewDto toDiaryPreviewDto(Diary diary, String loginId, Boolean liked) {
        return DiaryDto.DiaryPreviewDto.builder()
                .userId(diary.getUser().getUserId())
                .loginId(loginId)
                .diaryId(diary.getDiaryId())
                .nickName(diary.getUser().getNickName())
                .content(diary.getContent())
                .open(diary.getOpen())
                .musicId(diary.getMusic().getMusicId())
                .storedFilePath(diary.getMusic().getStoredFilePath())
                .liked(liked)
//                .writeDate(diary.getWriteDate())
                .build();
    }

    public static DiaryDto.DiaryResultDto toDiaryResultDto(Diary diary) {
        return DiaryDto.DiaryResultDto.builder()
                .diaryId(diary.getDiaryId())
                .userId(diary.getUser().getUserId())
                .content(diary.getContent())
                .open(diary.getOpen())
                .likeNum(diary.getLikeNum())
                .writeDate(diary.getWriteDate())
                .repLocationDto(LocationDtoConverter.locationToRepLocationDto(diary.getLocation()))
                .build();
    }
}
