package com.diary.bermuda.dto;

import com.google.gson.annotations.SerializedName;


public class LocationDto {

    public static class RepLocationDto {

        @SerializedName("addressName")
        private String addressName;         // 지번주소

        @SerializedName("latitude")          //위치
        private double latitude;

        @SerializedName("longitude")
        private double longitude;

/*        @SerializedName("categoryGroupCode")
        private String categoryGroupCode;
        @SerializedName("categoryGroupName")
        private String categoryGroupName;
        @SerializedName("categoryName")
        private String categoryName;

        @SerializedName("phone")
        private String phone;
        @SerializedName("placeName")
        private String placeName;
        @SerializedName("placeUrl")
        private String placeUrl;
        @SerializedName("roadAddressName")
        private String roadAddressName;*/

        //        private Point point;


/*        @SerializedName("diaryDtoList")
        private List<DiaryDto> diaryDtoList;*/

        public String getAddressName() {
            return addressName;
        }

        public void setAddressName(String addressName) {
            this.addressName = addressName;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return "RepLocationDto{" +
                    "addressName='" + addressName + '\'' +
                    ", latitude=" + latitude +
                    ", longitude=" + longitude +
                    '}';
        }

    }
}
