package com.diary.bermuda.dto;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class DiaryDto {
    // 일기 작성 (Fragment 3)

    public static class DiaryCreateDto {

        @SerializedName("content")
        private String content;

        @SerializedName("open")
        private boolean open;

        //일기 작성시 위치값도 넘겨주기 위해
        //DirayDto 안에 LocationDto의 ReplocationDto도 포함시켜 넘겨줌
        @SerializedName("repLocationDto")           //위치값
        private LocationDto.RepLocationDto repLocationDto;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isOpen() {
            return open;
        }

        public void setOpen(boolean open) {
            this.open = open;
        }

        public LocationDto.RepLocationDto getRepLocationDto() {
            return repLocationDto;
        }

        public void setRepLocationDto(LocationDto.RepLocationDto repLocationDto) {
            this.repLocationDto = repLocationDto;
        }

        @Override
        public String toString() {
            return "DiaryCreateDto{" +
                    "content='" + content + '\'' +
                    ", open=" + open +
                    ", repLocationDto=" + repLocationDto +
                    '}';
        }

    }

    // 내 일기 확인 (diart_list_Activity)
    public static class ShowDiaryDto{
        //@@@@@@@@@@@@@@@@@@@@  변수   --------------------------
        @SerializedName("diaryId")
        private Long diaryId;

        @SerializedName("userId")
        private String userId;

        @SerializedName("content")
        private String content;

        @SerializedName("open")
        private Boolean open;

        @SerializedName("writeDate")
        private LocalDate writeDate;

        @SerializedName("isMe")
        private Boolean isMe;

        @SerializedName("likeNum")
        private Integer likeNum;

        public Long getDiaryId() {
            return diaryId;
        }

        public void setDiaryId(Long diaryId) {
            this.diaryId = diaryId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Boolean getOpen() {
            return open;
        }

        public void setOpen(Boolean open) {
            this.open = open;
        }

        public LocalDate getWriteDate() {
            return writeDate;
        }

        public void setWriteDate(LocalDate writeDate) {
            this.writeDate = writeDate;
        }

        public Boolean getMe() {
            return isMe;
        }

        public void setMe(Boolean me) {
            isMe = me;
        }

        public Integer getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(Integer likeNum) {
            this.likeNum = likeNum;
        }

        //@@@@@@@@@@@@@@@@@@@@  toString   --------------------------
        @Override
        public String toString() {
            return "ShowDiaryDto{" +
                    "diaryId=" + diaryId +
                    ", userId='" + userId + '\'' +
                    ", content='" + content + '\'' +
                    ", open=" + open +
                    ", writeDate=" + writeDate +
                    ", isMe=" + isMe +
                    ", likeNum=" + likeNum +
                    '}';
        }

    }

    // 일기 수정
    public static class updateDiary{

        @SerializedName("content")
        private String content;

        @SerializedName("open")
        private Boolean open;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Boolean getOpen() {
            return open;
        }

        public void setOpen(Boolean open) {
            this.open = open;
        }

        //@@@@@@@@@@@@@@@@@@@@  toString   --------------------------
        @Override
        public String toString() {
            return "updateDiary{" +
                    "content='" + content + '\'' +
                    ", open=" + open +
                    '}';
        }

    }

    // 일기 삭제
    public static class deleteDiary{

    }

    public static class DiaryPreviewDto{

        @SerializedName("diaryId")
        private Long diaryId;

        @SerializedName("userId")
        private String userId;

        @SerializedName("loginId")
        private String loginId;

        @SerializedName("nickName")
        private String nickName;
        //    private String title;

        @SerializedName("content")
        private String content;

        @SerializedName("open")
        private Boolean open;

        @SerializedName("musicId")
        private Long musicId;

        @SerializedName("storedFilePath")
        private String storedFilePath;

        @SerializedName("liked")
        private Boolean liked;

//        @SerializedName("likedDiary")
//        private boolean likedDiary;

        public Long getDiaryId() {
            return diaryId;
        }

        public void setDiaryId(Long diaryId) {
            this.diaryId = diaryId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getLoginId() {
            return loginId;
        }

        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String userId) {
            this.nickName = userId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Boolean getOpen() {
            return open;
        }

        public void setOpen(Boolean open) {
            this.open = open;
        }

        public Long getMusicId() {
            return musicId;
        }

        public void setMusicId(Long musicId) {
            this.musicId = musicId;
        }

        public String getStoredFilePath() {
            return storedFilePath;
        }

        public void setStoredFilePath(String storedFilePath) {
            this.storedFilePath = storedFilePath;
        }

        public Boolean getliked() {
            return liked;
        }

        public void setliked(Boolean liked) {
            this.liked = liked;
        }

        @Override
        public String toString() {
            return "DiaryPreviewDto{" +
                    "diaryId=" + diaryId +
                    ", userId='" + userId + '\'' +
                    ", loginId='" + loginId + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", content='" + content + '\'' +
                    ", open=" + open +
                    ", musicId=" + musicId +
                    ", storedFilePath='" + storedFilePath + '\'' +
                    ", liked=" + liked +
                    '}';
        }
    }

}
