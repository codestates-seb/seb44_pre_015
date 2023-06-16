package com.mzdevelopers.serverapplication.answervote.mapper;

import com.mzdevelopers.serverapplication.answervote.dto.AnswerVoteDto;
import com.mzdevelopers.serverapplication.answervote.entity.AnswerVote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerVoteMapper {
    AnswerVote postDtoToAnswerVote(AnswerVoteDto.Post requestBody);
    AnswerVoteDto.Response answerVoteToAnswerVoteDtoResponse(AnswerVote answerVote);
}
