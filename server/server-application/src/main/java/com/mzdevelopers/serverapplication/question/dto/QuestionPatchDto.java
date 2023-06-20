package com.mzdevelopers.serverapplication.question.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class QuestionPatchDto {
    @NotBlank
    private String title;
    @NotBlank
    private String detail;
}
