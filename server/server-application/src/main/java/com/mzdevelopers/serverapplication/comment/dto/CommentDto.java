package com.mzdevelopers.serverapplication.comment.dto;

import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.member.dto.MemberInfoDto;
import lombok.*;

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
    @NoArgsConstructor
    public static class Response{
        private long commentId;
        private String commentDetail;
        private long answerId;
        @Setter
        private MemberInfoDto memberInfoDto;

        private String createdAt;
        private String updatedAt;

        @Builder
        public Response(long commentId,String commentDetail,
                        long answerId, MemberInfoDto memberInfoDto,
                        String createdAt, String updatedAt){
            this.commentId=commentId;
            this.commentDetail=commentDetail;
            this.answerId=answerId;
            this.memberInfoDto=memberInfoDto;
            this.createdAt=createdAt;
            this.updatedAt=updatedAt;
        }
    }
}
