package com.example.bermuda.config.security;


import com.example.bermuda.exception.exceptions.CUserNotFoundException;
import com.example.bermuda.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider { // JWT 토큰을 생성 및 검증 모듈

    @Value("spring.jwt.secret")
    private String secretKey;

    private long refreshTokenValidTime = 1000L * 60 * 60 * 24 * 14;
    private long accessTokenValidTime = 1000L * 60 * 60; // 1시간 토큰 유효

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // Access Token 생성.
    public String createAccessToken(String userPk, List<String> roles){
        return this.createToken(userPk, roles, accessTokenValidTime);
    }
    // Refresh Token 생성.
    public String createRefreshToken(String userPk, List<String> roles) {
        return this.createToken(userPk, roles, refreshTokenValidTime);
    }

    // Jwt 토큰 생성
    public String createToken(String userPk, List<String> roles, long tokenValid) {
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 데이터
                .setIssuedAt(now) // 토큰 발행일자
                .setExpiration(new Date(now.getTime() + tokenValid)) // 토큰 유효시간 설정
                .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘, 암호키
                .compact();
    }

    // Jwt 토큰으로 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Jwt 토큰에서 회원 구별 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 AccessToken 값을 가져옵니다. "authorization" : "token'
    public String resolveAccessToken(HttpServletRequest request) {
        if(request.getHeader("X-AUTH-ACCESS-TOKEN") != null )
            return request.getHeader("X-AUTH-ACCESS-TOKEN");
        return null;
    }
    // Request의 Header에서 RefreshToken 값을 가져옵니다. "authorization" : "token'
    public String resolveRefreshToken(HttpServletRequest request) {
        if(request.getHeader("X-AUTH-REFRESH-TOKEN") != null )
            return request.getHeader("X-AUTH-REFRESH-TOKEN");
        return null;
    }

    // Jwt 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
    // 어세스 토큰 헤더 설정
    public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
        response.setHeader("X-AUTH-ACCESS-TOKEN", accessToken);
    }

    // 리프레시 토큰 헤더 설정
    public void setHeaderRefreshToken(HttpServletResponse response, String refreshToken) {
        response.setHeader("X-AUTH-REFRESH-TOKEN", refreshToken);
    }

    // RefreshToken 존재유무 확인
    public boolean existsRefreshToken(String refreshToken) {
        return userRepository.existsByRefreshToken(refreshToken);
    }

    // userPk로 권한 정보 가져오기
    // userPk 자체가 userId를 String.valueOf로 바꾼거라 그대로 넣어도 크게 문제 없을 듯?
    public List<String> getRoles(String userPk) {
        return userRepository.findByUserId(userPk).orElseThrow(CUserNotFoundException::new).getRoles();
    }
}