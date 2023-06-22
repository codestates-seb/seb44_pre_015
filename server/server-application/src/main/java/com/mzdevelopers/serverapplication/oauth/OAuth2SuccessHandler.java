package com.mzdevelopers.serverapplication.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mzdevelopers.serverapplication.jwt.Token;
import com.mzdevelopers.serverapplication.jwt.TokenProvider;
import com.mzdevelopers.serverapplication.jwt.TokenService;
import com.mzdevelopers.serverapplication.member.dto.MemberDto;
import com.mzdevelopers.serverapplication.member.entity.Member;
import com.mzdevelopers.serverapplication.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final TokenProvider tokenProvider;
    private final UserRequestMapper userRequestMapper;
    private final ObjectMapper objectMapper;
    private final MemberRepository memberRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        MemberDto memberDto = userRequestMapper.toDto(oAuth2User);

        log.info("Principal에서 꺼낸 OAuth2User = {}", oAuth2User);
        log.info("토큰 발행 시작");

        Optional<Member> optionalMember = memberRepository.findByEmail(memberDto.getEmail());

        String token = tokenProvider.create(optionalMember.get());
        response.setHeader("Authorization", token);
        response.setHeader("UID",String.valueOf(optionalMember.get().getMemberId()));

        log.info("{}", token);

    }
}
