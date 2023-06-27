package com.mzdevelopers.serverapplication.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberQuestionDto {

    private long questionId;
    private String title;
    private String detail;
    private boolean solutionStatus;
}
