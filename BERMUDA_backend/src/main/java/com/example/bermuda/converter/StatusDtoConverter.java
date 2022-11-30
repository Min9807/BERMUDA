package com.example.bermuda.converter;

import com.example.bermuda.dto.StatusDto;
import org.springframework.stereotype.Component;

@Component
public class StatusDtoConverter {

    private static StatusDto statusDto;

    public static StatusDto returnSuccessStatusDto(){
        if(statusDto == null)
            statusDto = new StatusDto(
                    201,
                    "성공 하였습니다."
            );
        return statusDto;
    }
}
