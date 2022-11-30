package com.diary.bermuda.dto;

import com.google.gson.annotations.SerializedName;

public class ProfileImageDto {

    public static class ShowProfileImageDto {

        @SerializedName("byteArray")
        private String byteArray;

        public String getByteArray() {
            return byteArray;
        }

        public void setByteArray(String byteArray) {
            this.byteArray = byteArray;
        }

        @Override
        public String toString() {
            return "ShowProfileImageDto{" +
                    "byteArray='" + byteArray + '\'' +
                    '}';
        }

    }
}
