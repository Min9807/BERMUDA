package com.example.bermuda.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StatusDto {

    private Integer statusCode;
    private String statusMessage;
}
