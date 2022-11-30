package com.example.bermuda.domain;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ConfirmationToken {

    private static final long EMAIL_TOKEN_EXPIRATION_TIME_VALUE = 5L;	//토큰 만료 시간

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36, unique = true)
    @Schema(example = "인증코드 아이디")
    private String id;

    @Column
    @Schema(example = "만료 시각")
    private LocalDateTime expirationDate;

    @Column
    @Schema(example = "만료 됐는지")
    private boolean expired;

    //일부러 FK 사용 안함
    @Column
    @Schema(example = "연결된 유저 아이디")
    private String userId;

    @CreatedDate
    @Column(updatable = false)
    @Schema(example = "생성 시각")
    private LocalDateTime createDate;

    @LastModifiedDate
    @Schema(example = "마지막 수정 시각")
    private LocalDateTime lastModifiedDate;

    /**
     * 이메일 인증 토큰 생성
     * @param userId
     * @return
     */
    public static ConfirmationToken createEmailConfirmationToken(String userId){
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.expirationDate = LocalDateTime.now().plusMinutes(EMAIL_TOKEN_EXPIRATION_TIME_VALUE); // 5분후 만료
        confirmationToken.userId = userId;
        confirmationToken.expired = false;
        return confirmationToken;
    }

    /**
     * 토큰 사용으로 인한 만료
     */
    public void useToken(){
        expired = true;
    }
}