package com.mzdevelopers.serverapplication.answervote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;

public class AnswerVoteDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        @Positive
        private long memberId;
        @Positive
        @Setter
        private long answerId;
        private boolean isAnswerVoted;
    }
    @Getter
    @AllArgsConstructor
    public static class Response{
        private long answerVoteId;
        private long memberId;
        private long answerId;
        private boolean isAnswerVoted;
    }
}
