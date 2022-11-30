package com.example.bermuda.converter;

import com.example.bermuda.domain.Diary;
import com.example.bermuda.domain.VoiceActor;
import com.example.bermuda.dto.DiaryDto;
import com.example.bermuda.dto.VoiceActorDto;

public class VoiceActorDtoConverter {
    public static VoiceActorDto.StoredVoiceActorDto toStoredVoiceActor(VoiceActor voiceActor) {
        return VoiceActorDto.StoredVoiceActorDto.builder()
                .voicePitch(voiceActor.getVoicePitch())
                .voiceActorName(voiceActor.getVoiceActorName())
                .build();
    }
}
