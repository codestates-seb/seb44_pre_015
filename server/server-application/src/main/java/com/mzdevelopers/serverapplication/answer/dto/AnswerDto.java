package com.mzdevelopers.serverapplication.answer.dto;

import com.mzdevelopers.serverapplication.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

public class AnswerDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        @Positive
        private long questionId;
        @Positive
        private long memberId;
        @NotBlank
        private String detail;

    }
    @Getter
    @AllArgsConstructor
    public static class Patch{
        @Setter
        private long answerId;

        private String detail;
        private boolean solutionStatus;
    }

    @Getter
    @AllArgsConstructor
    public static class Response{
        private  long answerId;

        private String detail;

        private int votesCount;

        private boolean solutionStatus;

        private long questionId;

        private long memberId;
        private List<Comment> comments;
    }
}
