package com.example.bermuda.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Slf4j
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class User implements UserDetails {
        //유저가 사용할 아이디
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "유저의 primary key")
    private Long userNo;

    @Column(name = "user_id", unique = true)
    @Schema(example = "유저 아이디")
    private String userId;
    //비밀번호
    //@NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Schema(example = "유저 비밀번호")
    private String userPw;
    //이메일
    //@NotNull
    @Email
    @Column(unique = true)
    @Schema(example = "유저의 이메일")
    private String email;

    @Schema(example = "유저 본명")
    private String userName;
    @Schema(example = "유저 닉네임")
    private String nickName;

    @Schema(example = "유저 소개글")
    private String introduction;

    //userAuth??
    //@NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    @Schema(example = "유저의 사용자 등급")
    private List<String> roles = new ArrayList<>(); // 회원이 가지고 있는 권한 정보들

//    유저 프로필이 저장될 경로
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    @Schema(example = "유저의 프로필 사진")
//    private ProfileImage profileImage;

    @Schema(example = "유저를 팔로우 하는 숫자")
    private Integer followingNum;

    @Schema(example = "유저가 팔로우 하는 숫자")
    private Integer followerNum;

    @Schema(example = "제공자")
    private Boolean musicProvider;
    @Schema(example = "db에 저장된 리프레시 토큰")
    private String refreshToken;
    @Schema(example = "이메일 인증이 되었는가")
    private Boolean emailAuth;

    @Schema(example = "구독을 하고 있는가")
    private Boolean subscription;

    //내가 쓴 일기 리스트
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Diary> myDiaryList;

    //내가 작곡한 음악 리스트
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Music> myMusicList;

    //좋아요한 일기 리스트
    @OneToMany(mappedBy = "user")
    private List<UserDiary> storedDiaryList;

    //구매한 음악 리스트
    @OneToMany(mappedBy = "user")
    private List<UserMusic> storedMusicList;

    //구매한 성우 리스트
    @OneToMany(mappedBy = "user")
    private List<UserVoiceActor> storedVoiceActorList;


    public void updatePassword(String userPw){
        this.userPw = userPw;
    }

//    public void updateProfileImage(ProfileImage profileImage.) {this.profileImage = profileImage;}

    //이건 유저 이름 리턴하는건데 getUsername 오버라이드 된 것 때문에 오류 생겨서 getter로 안되는 듯
    public String getUserName(){
        return this.userName;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void emailVerifiedSuccess() {
        this.emailAuth = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.userPw;
    }

    //이건 아이디 넘겨주는것
    @Override
    public String getUsername() {
        return this.userId;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void updateSubscription(Boolean subscription){
        this.setSubscription(subscription);
    }
}
