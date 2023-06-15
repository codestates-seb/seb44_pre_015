package com.mzdevelopers.serverapplication.question.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class QuestionRequestDto {
    @NotBlank
    private String title;

    @NotBlank
    private String detail;

    @NotNull
    private Long memberId;
}
