//package com.mzdevelopers.serverapplication.member.service;
//
//import com.mzdevelopers.serverapplication.member.entity.memberInfo.GoogleMemberInfo;
//import com.mzdevelopers.serverapplication.member.entity.Member;
//import com.mzdevelopers.serverapplication.member.entity.memberInfo.OAuth2MemberInfo;
//import com.mzdevelopers.serverapplication.member.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class Oauth2MemberService extends DefaultOAuth2UserService {
//
//    private final MemberRepository memberRepository;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//        OAuth2MemberInfo memberInfo = null;
//
//        memberInfo = new GoogleMemberInfo(oAuth2User.getAttributes());
//
//        String provider = memberInfo.getProvider();
//        String name = memberInfo.getName();
//        String email = memberInfo.getEmail();
//
//        System.out.println("---------- 로그인 정보 ----------");
//        System.out.println(" 플랫폼 : " + provider);
//        System.out.println(" 이름  : " + name);
//        System.out.println(" 이메일 : " + email);
//        System.out.println("-------------------------------");
//
//        Optional<Member> findMember = memberRepository.findByEmail(email);
//
//        if(findMember.isEmpty()){
//
//            Member member = Member.builder()
//                    .name(name)
//                    .email(email)
//                    .provider(provider)
//                    .answer_vote_count(0)
//                    .build();
//
//            memberRepository.save(member);
//            System.out.println("!!! 신규 회원 등록 완료 !!!");
//        }
//        else {
//            System.out.println("!!! 이미 회원가입 되어있는 이메일입니다. !!!");
//        }
//
//        return oAuth2User;
//    }
//
//}
