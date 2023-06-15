package com.mzdevelopers.serverapplication.question.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class QuestionPatchDto {
    @NotBlank
    private String title;
    @NotBlank
    private String detail;
    @NotNull
    private long memberId;
}
