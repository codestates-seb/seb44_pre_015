package com.mzdevelopers.serverapplication.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mzdevelopers.serverapplication.exception.BusinessLogicException;
import com.mzdevelopers.serverapplication.exception.ExceptionCode;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
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
        if(optionalMember.isEmpty()){
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND);
        }

        String token = tokenProvider.create(optionalMember.get());
        String uid = String.valueOf(optionalMember.get().getMemberId());
//        response.setHeader("Authorization", token);
//        response.setHeader("UID",);
        log.info("{}", token);//dd

        String redirectUrl = createURI(token, uid).toString();
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);

    }

    private URI createURI(String accessToken, String uid) {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("access_token", accessToken);
        queryParams.add("UID", uid);

        return UriComponentsBuilder
                .newInstance()
                .scheme("https")
                .host("mz-developer-15.netlify.app")
//                .port(5173)
//                .path("/receive-token.html")
                .queryParams(queryParams)
                .build()
                .toUri();
    }
}
