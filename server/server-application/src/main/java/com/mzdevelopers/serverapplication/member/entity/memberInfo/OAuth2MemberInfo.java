package com.mzdevelopers.serverapplication.member.entity.memberInfo;

// 모든 Provider info 클래스에 공통적으로 구현되는 메서드
public interface OAuth2MemberInfo {

    String getProviderId();
    String getProvider();
    String getName();
    String getEmail();
}
