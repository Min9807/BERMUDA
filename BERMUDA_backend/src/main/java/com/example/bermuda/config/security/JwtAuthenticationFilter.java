package com.example.bermuda.config.security;

import com.example.bermuda.exception.exceptions.CUserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider; // JWT 토큰을 생성 및 검증 모듈 클래스

    // Jwt Provider 주입
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // Request로 들어오는 Jwt Token의 유효성을 검증 (jwtTokenProvider.validateToken)하는
    // filter를 filterChain에 등록한다.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 헤더에서 JWT 를 받아옵니다.
        String accessToken = jwtTokenProvider.resolveAccessToken(request);
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);

        // 유효한 토큰인지 확인합니다.
        if (accessToken != null) {
            log.info("토큰이 있다.");
            // 어세스 토큰이 유효한 상황
            if (jwtTokenProvider.validateToken(accessToken)) {
                this.setAuthentication(accessToken);
                log.info("토큰이 유효");
            }
            // 어세스 토큰이 만료된 상황 | 리프레시 토큰 또한 존재하는 상황
            else if (!jwtTokenProvider.validateToken(accessToken) && refreshToken != null) {
                log.info("access token이 만료");
                // 재발급 후, 컨텍스트에 다시 넣기
                /// 리프레시 토큰 검증
                boolean validateRefreshToken = jwtTokenProvider.validateToken(refreshToken);
                /// 리프레시 토큰 저장소 존재유무 확인
                boolean isRefreshToken = jwtTokenProvider.existsRefreshToken(refreshToken);
                if (validateRefreshToken && isRefreshToken) {
                    log.info("refresh token은 유효");
                    /// 리프레시 토큰으로 이메일 정보 가져오기
                    String userPk = jwtTokenProvider.getUserPk(refreshToken);
                    /// 이메일로 권한정보 받아오기
                    List<String> roles = jwtTokenProvider.getRoles(userPk);
                    /// 토큰 발급
                    String newAccessToken = jwtTokenProvider.createAccessToken(userPk, roles);
                    /// 헤더에 어세스 토큰 추가
                    jwtTokenProvider.setHeaderAccessToken(response, newAccessToken);
                    /// 컨텍스트에 넣기
                    this.setAuthentication(newAccessToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    // SecurityContext 에 Authentication 객체를 저장합니다.
    public void setAuthentication(String token) {
        // 토큰으로부터 유저 정보를 받아옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        // SecurityContext 에 Authentication 객체를 저장합니다.
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}