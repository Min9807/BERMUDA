package com.example.bermuda.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProfileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "프로필의 primary key")
    private Long profileImageId;
    @NotEmpty
    @Schema(example = "프로필 사진의 파일 원래 이름")
    private String originalFileName;
    @NotEmpty
    @Schema(example = "프로필 사진 파일이 저장된 경로")
    private String storedFilePath;

    @Schema(example = "파일의 사이즈")
    private long fileSize;

    // 프로필 이미지로 사용할 때 사용하는 속성
    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "user_no")
    @Schema(example = "프로필 사진의 소유자")
    private User user;

    public void updateProfileImage(String originalFileName, String storedFilePath, long fileSize) {
        this.originalFileName = originalFileName;
        this.storedFilePath = storedFilePath;
        this.fileSize = fileSize;
    }
}
