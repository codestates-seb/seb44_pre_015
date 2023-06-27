package com.mzdevelopers.serverapplication.comment.mapper;

import com.mzdevelopers.serverapplication.answer.dto.AnswerDto;
import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.comment.dto.CommentDto;
import com.mzdevelopers.serverapplication.comment.entity.Comment;
import com.mzdevelopers.serverapplication.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    default Comment commentPostToComment(CommentDto.Post commentPostDto){
        Comment comment = new Comment();

        Answer answer = new Answer();
        answer.setAnswerId(commentPostDto.getAnswerId());

        Member member = new Member();
        member.setMemberId(commentPostDto.getMemberId());

        comment.setMember(member);
        comment.setCommentDetail(commentPostDto.getCommentDetail());
        comment.setAnswer(answer);


        return comment;
    }
    Comment commentPatchToComment(CommentDto.Patch requestBody);
//    CommentDto.Response commentToCommentResponse(Comment comment);


    default CommentDto.Response commentToCommentResponse(Comment comment){
        CommentDto.Response commentResponse = CommentDto.Response.builder()
                .commentId(comment.getCommentId())
                .commentDetail(comment.getCommentDetail())
                .answerId(comment.getAnswer().getAnswerId())
//                .memberId(comment.getMember().getMemberId())
                .createdAt(String.valueOf(comment.getCreatedAt()))
                .updatedAt(String.valueOf(comment.getUpdatedAt()))

                .build();
        return commentResponse;
    }
    List<CommentDto.Response> commentsToCommentResponses(List<Comment> comments);
}
