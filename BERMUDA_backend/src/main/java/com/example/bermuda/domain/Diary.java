package com.example.bermuda.domain;

import com.example.bermuda.exception.exceptions.CAuthenticationException;
import com.example.bermuda.exception.exceptions.CNotAuthorizedUserException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    @Schema(example = "일기장 아이디")
    private Long diaryId;

//    @Schema(example = "제목")
//    private String title;
    @Schema(example = "내용")
    private String content;

    @Schema(example = "공개 여부")
    private Boolean open;
    @Schema(example = "좋아요 개수")
    private Integer likeNum;
    @Schema(example = "작성 일자")
    private LocalDate writeDate;


    @ManyToOne
    @JoinColumn(name = "location_id")
    @Schema(example = "일기 작성한 위치")
    @JsonBackReference
    private Location location;

    //일기의 주인
    @ManyToOne
    @JoinColumn(name = "user_no")
    @Schema(example = "연결된 유저")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "music_id")
    @Schema(example = "일기와 관련된 음악")
    @JsonBackReference
    private Music music;

    //일기를 좋아요한 유저 리스트
    @OneToMany(mappedBy = "diary")
    private List<UserDiary> storedUserList;

    public void checkUser(){
        String userId;
        try {
            //권한을 통해 유저 id를 획득합니다.
            userId = SecurityContextHolder.getContext().getAuthentication().getName();
        }catch(NullPointerException e){
            log.info("정상적으로 로그인 되지 않았습니다.");
            throw new CAuthenticationException();
        }
        if(!this.getUser().getUserId().equals(userId)){
            log.info("로그인한 유저와 일기의 유저가 다릅니다.");
            throw new CNotAuthorizedUserException();
        }
    }

    public boolean checkUser(boolean isMe){
        String userId;
        //권한을 통해 유저 id를 획득합니다.
        try {
            //권한을 통해 유저 id를 획득합니다.
            userId = SecurityContextHolder.getContext().getAuthentication().getName();
        }catch(NullPointerException e){
            log.info("정상적으로 로그인 되지 않았습니다.");
            throw new CAuthenticationException();
        }
        if(!this.getUser().getUserId().equals(userId)) {
            log.info("로그인한 유저와 일기의 유저가 다릅니다.");
            isMe = false;
        }
        return isMe;
    }

    public void update(String content, Boolean open){
        this.content = content;
        this.open = open;
    }
}
