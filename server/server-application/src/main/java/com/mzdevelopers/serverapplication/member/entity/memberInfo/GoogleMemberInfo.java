package com.mzdevelopers.serverapplication.member.entity.memberInfo;

import com.mzdevelopers.serverapplication.member.entity.memberInfo.OAuth2MemberInfo;

import java.util.Map;

public class GoogleMemberInfo implements OAuth2MemberInfo {

    private Map<String, Object> attributes;

    public GoogleMemberInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId(){
        return (String) attributes.get("id");
    }

    @Override
    public String getProvider(){
        return "google";
    }

    @Override
    public String getName(){
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail(){
        return (String) attributes.get("email");
    }

}
