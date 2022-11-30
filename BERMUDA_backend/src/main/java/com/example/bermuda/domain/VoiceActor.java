package com.example.bermuda.domain;

import com.example.bermuda.domain.voice_type.VoicePitch;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class VoiceActor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "성우 primary key")
    private Long voiceActorId;

    @Schema(example = "성우 이름")
    private String voiceActorName;

    @Schema(example = "성우 목소리 스타일")
    private VoicePitch voicePitch;

    //성우 구매자 리스트
    @Schema(example = "성우 소유자")
    @OneToMany(mappedBy = "voiceActor")
    private List<UserVoiceActor> storedUserList;
}