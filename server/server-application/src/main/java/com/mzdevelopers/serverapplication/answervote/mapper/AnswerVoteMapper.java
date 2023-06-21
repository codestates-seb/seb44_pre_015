package com.mzdevelopers.serverapplication.answervote.mapper;

import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.answervote.dto.AnswerVoteDto;
import com.mzdevelopers.serverapplication.answervote.entity.AnswerVote;
import com.mzdevelopers.serverapplication.question.stub.MemberStub;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerVoteMapper {
    default AnswerVote answerVotePostDtoToAnswerVote(AnswerVoteDto.Post answerVotePostDto){
        AnswerVote answerVote = new AnswerVote();
        Answer answer = new Answer();
        answer.setAnswerId(answerVotePostDto.getAnswerId());

        MemberStub memberStub = new MemberStub();
        memberStub.setMemberId(answerVotePostDto.getMemberId());

        answerVote.setMemberStub(memberStub);
        answerVote.setAnswerVoted(answerVotePostDto.isAnswerVoted());

        answerVote.setAnswer(answer);

        return answerVote;
    }
    default AnswerVoteDto.Response answerVoteToAnswerVoteDtoResponse(AnswerVote answerVote){
        AnswerVoteDto.Response answerVoteResponse = new AnswerVoteDto.Response(
                answerVote.getAnswerVoteId(),
                answerVote.getMemberStub().getMemberId(),
                answerVote.getAnswer().getAnswerId(),
                answerVote.isAnswerVoted()
        );
        return answerVoteResponse;
    }
}
