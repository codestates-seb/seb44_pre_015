package com.mzdevelopers.serverapplication.answervote.mapper;

import com.mzdevelopers.serverapplication.answervote.dto.AnswerVoteDto;
import com.mzdevelopers.serverapplication.answervote.entity.AnswerVote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerVoteMapper {
    AnswerVote postDtoToAnswerVote(AnswerVoteDto.Post requestBody);
    default AnswerVoteDto.Response answerVoteToAnswerVoteDtoResponse(AnswerVote answerVote){
        AnswerVoteDto.Response answerVoteResponse = new AnswerVoteDto.Response(
                answerVote.getAnswerVoteId(),
                answerVote.getMemberId(),
                answerVote.getAnswer().getAnswerId(),
                answerVote.isAnswerVoted()
        );
        return answerVoteResponse;
    }
}
