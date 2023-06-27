package com.mzdevelopers.serverapplication.oauth;

import com.mzdevelopers.serverapplication.member.dto.MemberDto;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserRequestMapper implements UserRequestMapper {

    @Override
    public MemberDto toDto(OAuth2User oAuth2User) {
        String email = extractEmail(oAuth2User);
        String name = extractName(oAuth2User);
        String picture = extractPicture(oAuth2User);

        return MemberDto.builder()
                .email(email)
                .name(name)
                .picture(picture)
                .build();
    }

    private String extractEmail(OAuth2User oAuth2User) {
        // OAuth2User에서 이메일 정보를 추출하는 로직을 작성합니다.
        // 예를 들어, Google의 경우 "email" 속성을 사용할 수 있습니다.
        return oAuth2User.getAttribute("email");
    }

    private String extractName(OAuth2User oAuth2User) {
        // OAuth2User에서 이름 정보를 추출하는 로직을 작성합니다.
        // 예를 들어, Google의 경우 "name" 속성을 사용할 수 있습니다.
        return oAuth2User.getAttribute("name");
    }

    private String extractPicture(OAuth2User oAuth2User) {
        // OAuth2User에서 프로필 사진 정보를 추출하는 로직을 작성합니다.
        // 예를 들어, Google의 경우 "picture" 속성을 사용할 수 있습니다.
        return oAuth2User.getAttribute("picture");
    }
}