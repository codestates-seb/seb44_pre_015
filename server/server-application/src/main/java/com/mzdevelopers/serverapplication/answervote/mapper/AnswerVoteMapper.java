package com.mzdevelopers.serverapplication.answervote.mapper;

import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.answervote.dto.AnswerVoteDto;
import com.mzdevelopers.serverapplication.answervote.entity.AnswerVote;
import com.mzdevelopers.serverapplication.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerVoteMapper {
    default AnswerVote answerVotePostDtoToAnswerVote(AnswerVoteDto.Post answerVotePostDto){
        AnswerVote answerVote = new AnswerVote();
        Answer answer = new Answer();
        answer.setAnswerId(answerVotePostDto.getAnswerId());

        Member member = new Member();
        member.setMemberId(answerVotePostDto.getMemberId());

        answerVote.setMember(member);
        answerVote.setAnswerVoted(answerVotePostDto.isAnswerVoted());

        answerVote.setAnswer(answer);

        return answerVote;
    }
    default AnswerVoteDto.Response answerVoteToAnswerVoteDtoResponse(AnswerVote answerVote){
        AnswerVoteDto.Response answerVoteResponse = new AnswerVoteDto.Response(
                answerVote.getAnswerVoteId(),
                answerVote.getMember().getMemberId(),
                answerVote.getAnswer().getAnswerId(),
                answerVote.isAnswerVoted()
        );
        return answerVoteResponse;
    }
}
