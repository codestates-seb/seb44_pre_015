package com.mzdevelopers.serverapplication.question.dto;

import com.mzdevelopers.serverapplication.tag.dto.TagNameDto;
import com.mzdevelopers.serverapplication.tag.entity.QuestionTag;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
public class QuestionPatchDto {
    @NotBlank
    private String title;
    @NotBlank
    private String detail;

    private List<TagNameDto> tags;
}
