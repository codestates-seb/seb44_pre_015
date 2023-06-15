package com.mzdevelopers.serverapplication.question.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionResponseDto {
    private String title;
    private String detail;
    private boolean solutionStatus;
    private int answerCount;
    private int votesCount;
    private int viewCount;
    private Long memberId;
    private List<StubAnswer> answers;
}
