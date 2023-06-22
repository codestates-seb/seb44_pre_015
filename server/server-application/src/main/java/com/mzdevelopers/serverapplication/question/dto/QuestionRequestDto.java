package com.mzdevelopers.serverapplication.question.dto;

import com.mzdevelopers.serverapplication.tag.dto.TagNameDto;
import com.mzdevelopers.serverapplication.tag.entity.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
@ToString
public class QuestionRequestDto {
    @NotBlank
    private String title;

    @NotBlank
    private String detail;

    @NotNull
    private Long memberId;

    private List<TagNameDto> tags;
}
