package com.mzdevelopers.serverapplication.jwt;

import com.mzdevelopers.serverapplication.exception.BusinessLogicException;
import com.mzdevelopers.serverapplication.exception.ExceptionCode;
import com.mzdevelopers.serverapplication.member.dto.MemberDto;
import com.mzdevelopers.serverapplication.member.entity.Member;
import com.mzdevelopers.serverapplication.member.repository.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.ap.shaded.freemarker.template.utility.StringUtil;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = parseBearerToken(request);
            log.info("JwtAuthFilter is running....");
            if (token != null && !token.equalsIgnoreCase("null")) {
                String email = tokenProvider.validateAndGetMemberEmail(token);
                log.info("Authenticated Member Email : " + email);
                AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, null, AuthorityUtils.NO_AUTHORITIES);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authenticationToken);
                SecurityContextHolder.setContext(securityContext);
            }
        } catch (Exception e) {
            log.error("Could not set user authentication in security context", e);
            throw new BusinessLogicException(ExceptionCode.INVALID_TOKEN_STATUS);
        }

        filterChain.doFilter(request, response);
    }

    private String parseBearerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends GenericFilterBean {
//
//    private final TokenService tokenService;
//    private final MemberRepository memberRepository;
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String accessToken = tokenService.extractAccessToken(httpRequest)
//                .filter(tokenService::isTokenValid)
//                .orElse(null);
//
//        System.out.println(httpRequest.getRequestURI());
//
//        if (accessToken != null) {
//            String email = String.valueOf(tokenService.extractEmail(accessToken));
//            Optional<Member> member = memberRepository.findByEmail(email);
//            SecurityContextHolder.getContext().setAuthentication(getAuthentication(member.get()));
//        } else if (!httpRequest.getRequestURI().equals("/login")) {
//            throw new ServletException("다시 로그인해주세요.");
//        }
//
////        if (token != null && tokenService.isTokenValid(token)) {
////            String email = String.valueOf(tokenService.extractEmail(token));
////
////            MemberDto memberDto = MemberDto.builder()
////                    .email(email)
////                    .name("name")
////                    .picture("pic")
////                    .build();
////
////            Authentication authentication = getAuthentication(memberDto);
////            SecurityContextHolder.getContext().setAuthentication(authentication);
////        }
//
//        chain.doFilter(request, response);
//    }
//
//    public Authentication getAuthentication(Member member) {
//        return new UsernamePasswordAuthenticationToken(member, "", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
//    }
//}
