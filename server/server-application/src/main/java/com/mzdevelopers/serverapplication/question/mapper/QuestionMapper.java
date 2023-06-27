package com.mzdevelopers.serverapplication.question.mapper;

import com.mzdevelopers.serverapplication.member.entity.Member;
import com.mzdevelopers.serverapplication.question.dto.QuestionPatchRequestDto;
import com.mzdevelopers.serverapplication.question.dto.QuestionRequestDto;
import com.mzdevelopers.serverapplication.question.dto.QuestionResponseDto;
import com.mzdevelopers.serverapplication.question.entity.Question;
import com.mzdevelopers.serverapplication.question.service.QuestionServiceImpl;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuestionMapper {
    default Question questionRequestDtoToQuestion(QuestionRequestDto questionRequestDto){
        Question question = new Question();

        Member member = new Member();
        member.setMemberId(questionRequestDto.getMemberId());

        question.setTitle(questionRequestDto.getTitle());
        question.setDetail(questionRequestDto.getDetail());

        question.setMember(member);

        return question;
    };

    default QuestionResponseDto questionToQuestionResponseDto(Question question){
        QuestionResponseDto questionResponseDto = new QuestionResponseDto();
        questionResponseDto.setQuestionId(question.getQuestionId());
        questionResponseDto.setTitle(question.getTitle());
        questionResponseDto.setDetail(question.getDetail());
        questionResponseDto.setSolutionStatus(question.isSolutionStatus());
        questionResponseDto.setAnswerCount(question.getAnswerCount());
        questionResponseDto.setVotesCount(question.getVotesCount());
        questionResponseDto.setViewCount(question.getViewCount());

        questionResponseDto.setCreatedAt(String.valueOf(question.getCreatedAt()));
        questionResponseDto.setUpdatedAt(String.valueOf(question.getUpdatedAt()));

        return questionResponseDto;
    };

    QuestionPatchRequestDto questionToQuestionPatchRequestDto(Question question);
}
