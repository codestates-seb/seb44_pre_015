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

        member.setMemberId(requestBody.getMemberId());
        answer.setQuestion(question);
        answer.setMember(member);
        answer.setDetail(requestBody.getDetail());
        return answer;
    };
    Answer answerPatchToAnswer(AnswerDto.Patch requestBody);



    default AnswerDto.Response answerToAnswerResponse(Answer answer){
        AnswerDto.Response answerResponse = AnswerDto.Response.builder()
                .answerId(answer.getAnswerId())
                .detail(answer.getDetail())
                .votesCount(answer.getVotesCount())
                .solutionStatus(answer.isSolutionStatus())
                .questionId(answer.getQuestion().getQuestionId())
                .createdAt(String.valueOf(answer.getCreatedAt()))
                .updatedAt(String.valueOf(answer.getUpdatedAt()))
                .build();
//        AnswerDto.Response answerResponse = new AnswerDto.Response(
//                answer.getAnswerId(),
//                answer.getDetail(),
//                answer.getVotesCount(),
//                answer.isSolutionStatus(),
//                answer.getQuestion().getQuestionId(),
//                answer.getMember().getMemberId(),
//                answer.getAnswerVotes().
//                answer.getComments()
//        );
//        answerResponse.setCreatedAt(String.valueOf(answer.getCreatedAt()));
//        answerResponse.setUpdatedAt(String.valueOf(answer.getUpdatedAt()));
        return answerResponse;
    };

    List<AnswerDto.Response> answersToAnswerResponses(List<Answer> answers);
}
