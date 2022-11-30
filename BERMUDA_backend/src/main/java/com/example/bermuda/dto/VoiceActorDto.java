package com.example.bermuda.dto;

import com.example.bermuda.domain.UserVoiceActor;
import com.example.bermuda.domain.voice_type.VoicePitch;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class VoiceActorDto {

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class RepVoiceActorDto {

        private Long voiceActorId;

        private String voiceActorName;

        private VoicePitch voicePitch;

        //성우 구매자 리스트
        private List<UserVoiceActor> storedUserList;

    }

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class StoredVoiceActorDto {

        private Long voiceActorId;

        private String voiceActorName;

        private VoicePitch voicePitch;
    }
}

