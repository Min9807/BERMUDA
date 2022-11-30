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
public class UserMusic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    @Schema(example = "user music 다대 다 primary key")
    private Long userMusicId;

    @Schema(example = "user 다대 일 관계")
    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @Schema(example = "music 다대 일 관계")
    @ManyToOne
    @JoinColumn(name = "music_id")
    private Music music;
}
