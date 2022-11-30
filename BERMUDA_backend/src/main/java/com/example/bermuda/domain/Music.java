package com.example.bermuda.domain;

import com.example.bermuda.domain.music_type.AtmosphereCode;
import com.example.bermuda.domain.music_type.AtmosphereDrum;
import com.example.bermuda.domain.music_type.BPM;
import com.example.bermuda.domain.music_type.Background;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "음악 primary key")
    private Long musicId;

    @Schema(example = "음악 drum 분위기")
    private AtmosphereDrum atmosphereDrum;
    @Schema(example = "음악 코드 분위기")
    private AtmosphereCode atmosphereCode;
    @Schema(example = "백그라운드")
    @Enumerated(EnumType.STRING)
    private Background background;
    @Schema(example = "속도")
    private BPM bpm;

    @JoinColumn(name = "mood")
    @Schema(example = "분위기")
    private String mood;
    @NotEmpty
    @Schema(example = "음악 파일의 원래 이름")
    private String originalFileName;
    @NotEmpty
    @Schema(example = "저장된 경로")
    private String storedFilePath;

    @Schema(example = "파일의 경로")
    private long fileSize;

    //해당 음악의 작곡가
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_no")
    @Schema(example = "음악의 작곡가")
    private User user;

    //음악 구매자 리스트
    @OneToMany(mappedBy = "music")
    @Schema(example = "음악을 소유한 사람들 과의 다대다 관계")
    private List<UserMusic> storedUserList;

    // 음악과 일기 매핑
    @OneToMany(mappedBy = "music")
    @JsonManagedReference
    @Schema(example = "음악이 사용된 일기 리스트")
    private List<Diary> diaryList;

    public void updateMusic(String originalFileName, String storedFilePath, long fileSize) {
        this.originalFileName = originalFileName;
        this.storedFilePath = storedFilePath;
        this.fileSize = fileSize;
    }

    public void addDiaryList(Diary diary){
        if (this.diaryList == null)
            diaryList = new ArrayList<>();
        diaryList.add(diary);
        diary.setMusic(this);
    }
}