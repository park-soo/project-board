package com.project.board.config;

import com.project.board.dto.security.BoardPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext())    // 시큐리티의 모든 정보를 들고 있는 class
                .map(SecurityContext::getAuthentication)    // SecurityContext 불러온다.
                .filter(Authentication::isAuthenticated) // 필터를 통해 검증한다.
                .map(Authentication::getPrincipal) // 보편적인 Principal(user Detail)을 불러온다.
                .map(BoardPrincipal.class::cast)    // 유저디테일로 부터 만든정보를 받는다. 타입 캐스팅
                .map(BoardPrincipal::getUsername);  // username 불러온다.
    }
}
