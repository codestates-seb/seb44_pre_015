package com.mzdevelopers.serverapplication.member.config;

import com.mzdevelopers.serverapplication.member.service.Oauth2MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // MemberService Di 하기
    @Autowired
    private final Oauth2MemberService oauth2MemberService;

    public SecurityConfiguration(Oauth2MemberService oauth2MemberService) {
        this.oauth2MemberService = oauth2MemberService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf() // csrf 기능
                .disable() // 안씀

                .headers() // 여기서부터
                .frameOptions().disable() // 여기까지는 h2-console 접근 가능하게 하는 역할
                .and()

                .formLogin().disable()
                .httpBasic().disable()

                .authorizeHttpRequests() // URL 별로 접근 권한 설정
                .antMatchers("/", "/h2-console/**", "/v1/members/**").permitAll() // 이 경로들은 아무나 접근 가능
                .anyRequest().authenticated() // 그 외 경로들은 인증 받은 사람만 접근가능
                .and()

                .logout() // 로그아웃 되면 자동으로
                .logoutSuccessUrl("/") // 이 경로로 이동
                .and()

                .oauth2Login() // 로그인 기능 적용
                .defaultSuccessUrl("/login-success") // 이 경로로 이동
                .userInfoEndpoint() // 로그인 이후 사용자 정보 가져오는 설정
                .userService(oauth2MemberService); // 로그인에 성공하면 유저 데이터 가지고 MemberService 에서 처리하겠다

        return http.build();
    }
}
