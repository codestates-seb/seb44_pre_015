package com.mzdevelopers.serverapplication.member.service;

import com.mzdevelopers.serverapplication.member.entity.memberInfo.GoogleMemberInfo;
import com.mzdevelopers.serverapplication.member.entity.Member;
import com.mzdevelopers.serverapplication.member.entity.memberInfo.OAuth2MemberInfo;
import com.mzdevelopers.serverapplication.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class Oauth2MemberService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2MemberInfo memberInfo = null;

        String providerId = userRequest.getClientRegistration().getRegistrationId();

        memberInfo = new GoogleMemberInfo(oAuth2User.getAttributes());


        String provider = memberInfo.getProvider();
        String name = memberInfo.getName();
        String email = memberInfo.getEmail();

        System.out.println("---------- 로그인 정보 ----------");
        System.out.println(" 플랫폼 : " + provider);
        System.out.println(" 이름  : " + name);
        System.out.println(" 이메일 : " + email);
        System.out.println("-------------------------------");

        Optional<Member> findMember = memberRepository.findByEmail(email);

        Member member = null;

        if(findMember.isEmpty()){

            member = Member.builder()
                    .name(name)
                    .email(email)
                    .provider(provider)
                    .build();

            memberRepository.save(member);
        }
        else {
            System.out.println("!!! 이미 회원가입 되어있는 이메일입니다. !!!");
        }

        return oAuth2User;
    }

    public Optional<Member> findMemberByEmail(String email){

        return memberRepository.findByEmail(email);
    }

    // 모든 회원 출력
    public List<Member> findMembers(){

        return memberRepository.findAll();
    }
}