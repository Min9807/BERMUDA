package com.example.bermuda.dto;

import lombok.*;
import org.locationtech.jts.geom.Point;

import java.util.List;

public class LocationDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RepLocationDto {
        private String addressName; // 지번주소
        private String roadAddressName; // 도로명주소

        private Point point;

        private double longitude;
        private double latitude;

        private List<DiaryDto> diaryDtoList;
    }

    //Location 엔티티와 관련있기 보단 좌표 필드 자체를 써야하는 객체
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LocationCoordinateDto {
        private Double longitude; //경도
        private Double latitude;//위도
    }
}
