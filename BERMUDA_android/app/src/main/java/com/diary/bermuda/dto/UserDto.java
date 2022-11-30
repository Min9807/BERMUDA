package com.diary.bermuda.dto;

import com.google.gson.annotations.SerializedName;

public class UserDto {

    //회원가입 Dto
    public static class SignupUserDto {

        @SerializedName("userId")               //Id
        private String userId;

        @SerializedName("userPw")               //Pw
        private String userPw;

        @SerializedName("email")                //Email
        private String email;

        @SerializedName("nickName")             //닉네임
        private String nickName;

        @SerializedName("userName")             //사용자 이름
        private String userName;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserPw() {
            return userPw;
        }

        public void setUserPw(String userPw) {
            this.userPw = userPw;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        @Override
        public String toString() {
            return "SignupUserDto{" +
                    "userId='" + userId + '\'' +
                    ", userPw='" + userPw + '\'' +
                    ", email='" + email + '\'' +
                    ", nickname='" + nickName + '\'' +
                    ", userName='" + userName + '\'' +
                    '}';
        }
    }
    // 비번 찾기
    public static class FindPwDto {

        @SerializedName("userId")
        private String userId;
        @SerializedName("email")
        private String email;
        @SerializedName("userName")
        private String userName;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

    }

    //비번 변경
    public static class ChangePwDto{

        @SerializedName("curPw")
        private String curPw;

        @SerializedName("newPw")
        private String newPw;

        public String getCurPw() {
            return curPw;
        }

        public void setCurPw(String curPw) {
            this.curPw = curPw;
        }

        public String getNewPw() {
            return newPw;
        }

        public void setNewPw(String newPw) {
            this.newPw = newPw;
        }

    }

}
