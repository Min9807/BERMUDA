package com.example.bermuda.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Location{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    @Schema(example = "위치 db primary key")
    private Long locationId;

    //지번 주소
//    @NotNull
    @Schema(example = "지번 주소")
    private String addressName; // 지번주소
    @Schema(example = "도로명 주소")
    private String roadAddressName; // 도로명주소

    @Schema(example = "mysql 위치 객체")
    private Point point;
//
//    @NotNull
//    private Double longitude; //경도
//
//    @NotNull
//    private Double latitude;//위도

    @OneToMany(mappedBy = "location")
    @JsonManagedReference
    @Schema(example = "해당 주소에 있는 일기")
    private List<Diary> diaryList;

    public void addDiaryList(Diary diary){
        if (this.diaryList == null)
            diaryList = new ArrayList<>();
        diaryList.add(diary);
        diary.setLocation(this);
    }
}