package com.mzdevelopers.serverapplication.oauth;

import com.mzdevelopers.serverapplication.member.dto.MemberDto;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface UserRequestMapper {
    MemberDto toDto(OAuth2User oAuth2User);
}
