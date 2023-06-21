package com.mzdevelopers.serverapplication.comment.dto;

import com.mzdevelopers.serverapplication.answer.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CommentDto {
    @Getter
    @AllArgsConstructor
    public static class Post{
        @NotBlank
        private String commentDetail;
        @Positive
        private long answerId;
        @Positive
        private long memberId;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch{
        @Setter
        private long commentId;
        @NotBlank
        private String commentDetail;
    }

    @Getter
    @AllArgsConstructor
    public static class Response{
        private long commentId;
        private String commentDetail;
        private long answerId;
        private long memberId;
    }
}
