package com.mzdevelopers.serverapplication.answervote.dto;

import com.mzdevelopers.serverapplication.answer.entity.Answer;
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
        private long answerId;
        private boolean answerVoted;

        public Answer getAnswer(){
            Answer answer = new Answer();
            answer.setAnswerId(answerId);
            return answer;
        }
    }
    @Getter
    @AllArgsConstructor
    public static class Response{
        private long answerVoteId;
        private long memberId;
        private long answerId;
        private boolean answerVoted;
    }
}
