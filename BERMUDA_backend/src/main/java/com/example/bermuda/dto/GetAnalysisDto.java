package com.example.bermuda.dto;

import lombok.*;

public class GetAnalysisDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseBackgroundDto {
        private String background;
    }
}
