package com.mzdevelopers.serverapplication.question.mapper;

import com.mzdevelopers.serverapplication.question.dto.QuestionRequestDto;
import com.mzdevelopers.serverapplication.question.dto.QuestionResponseDto;
import com.mzdevelopers.serverapplication.question.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionMapper {
    Question questionRequestDtoToQuestion(QuestionRequestDto questionRequestDto);

    QuestionResponseDto questionToQuestionResponseDto(Question question);
}
