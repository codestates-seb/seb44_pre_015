package com.mzdevelopers.serverapplication.member.dto;

import com.mzdevelopers.serverapplication.question.entity.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MemberResponseDto {

    private long memberId;
    private String userName;
    private String picture;
    private List<MemberQuestionDto> questions;
}
