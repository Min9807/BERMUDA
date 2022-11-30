package com.example.bermuda.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserVoiceActor {

    @Id
    @Schema(example = "user voice 다대 다 관계 primary key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long userVoiceActorId;

    @Schema(example = "user 다대 일")
    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @Schema(example = "voice 다대 일")
    @ManyToOne
    @JoinColumn(name = "voice_actor_id")
    private VoiceActor voiceActor;
}
