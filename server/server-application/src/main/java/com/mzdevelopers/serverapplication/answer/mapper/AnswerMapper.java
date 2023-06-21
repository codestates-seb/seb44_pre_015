package com.mzdevelopers.serverapplication.answer.mapper;

import com.mzdevelopers.serverapplication.answer.dto.AnswerDto;
import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.member.entity.Member;
import com.mzdevelopers.serverapplication.question.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    default Answer answerPostToAnswer(AnswerDto.Post requestBody){
        Answer answer =new Answer();
        Question question = new Question();
        Member member = new Member();
        question.setQuestionId(requestBody.getQuestionId());

        member.setId(requestBody.getMemberId());
        answer.setQuestion(question);
        answer.setMember(member);
        answer.setDetail(requestBody.getDetail());
        return answer;
    };
    Answer answerPatchToAnswer(AnswerDto.Patch requestBody);
    default AnswerDto.Response answerToAnswerResponse(Answer answer){
        AnswerDto.Response answerResponse = new AnswerDto.Response(
                answer.getAnswerId(),
                answer.getDetail(),
                answer.getVotesCount(),
                answer.isSolutionStatus(),
                answer.getQuestion().getQuestionId(),
                answer.getMember().getId(),
                answer.getComments()
        );
        return answerResponse;
    };

    List<AnswerDto.Response> answersToAnswerResponses(List<Answer> members);
}
