package com.mzdevelopers.serverapplication.question.dto;

import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.question.stub.StubAnswer;
import com.mzdevelopers.serverapplication.tag.dto.TagDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class QuestionResponseDto {
    private long questionId;
    private String title;
    private String detail;
    private boolean solutionStatus;
    private int answerCount;
    private int votesCount;
    private int viewCount;
    private Long memberId;
    private List<TagDto> tags;
    private List<Answer> answers;
    private String createdAt;
    private String updatedAt;
}
