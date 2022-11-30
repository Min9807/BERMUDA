package com.example.bermuda.converter;

import com.example.bermuda.domain.Location;
import com.example.bermuda.dto.LocationDto;

public class LocationDtoConverter {
    public static LocationDto.RepLocationDto locationToRepLocationDto(Location location){
        return LocationDto.RepLocationDto.builder()
                .addressName(location.getAddressName())
                .roadAddressName(location.getRoadAddressName())
                .point(location.getPoint())
                //.diaryDtoList(location.getDiaryList())
                .build();
    }
}
