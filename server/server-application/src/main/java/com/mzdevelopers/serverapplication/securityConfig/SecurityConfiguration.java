package com.mzdevelopers.serverapplication.securityConfig;

import com.mzdevelopers.serverapplication.jwt.JwtAuthenticationFilter;
import com.mzdevelopers.serverapplication.jwt.TokenService;
import com.mzdevelopers.serverapplication.member.repository.MemberRepository;
import com.mzdevelopers.serverapplication.oauth.CustomOAuth2UserService;
import com.mzdevelopers.serverapplication.oauth.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

//    private final Oauth2MemberService oauth2MemberService;
    private final MemberRepository memberRepository;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final TokenService tokenService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf() // csrf 기능
                .disable() // 안씀
                .cors().disable()
                .headers() // 여기서부터
                .frameOptions().disable() // 여기까지는 h2-console 접근 가능하게 하는 역할
                .and()

                .formLogin().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeHttpRequests() // URL 별로 접근 권한 설정
                .antMatchers("/", "/h2/**", "/auth/**", "/oauth2/**", "/test").permitAll() // 이 경로들은 아무나 접근 가능
                .anyRequest().authenticated() // 그 외 경로들은 인증 받은 사람만 접근가능
                .and()

                .oauth2Login()
                .defaultSuccessUrl("/test")
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .successHandler(oAuth2SuccessHandler);
        http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);


        return http.build();
    }

}
