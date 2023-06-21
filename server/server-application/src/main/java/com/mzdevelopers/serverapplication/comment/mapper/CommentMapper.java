package com.mzdevelopers.serverapplication.comment.mapper;

import com.mzdevelopers.serverapplication.answer.dto.AnswerDto;
import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.comment.dto.CommentDto;
import com.mzdevelopers.serverapplication.comment.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    default Comment commentPostToComment(CommentDto.Post commentPostDto){
        Comment comment = new Comment();

        Answer answer = new Answer();
        answer.setAnswerId(commentPostDto.getAnswerId());

        comment.setCommentDetail(commentPostDto.getCommentDetail());
        comment.setAnswer(answer);
        comment.setMemberId(commentPostDto.getMemberId());

        return comment;
    }
    Comment commentPatchToComment(CommentDto.Patch requestBody);
//    CommentDto.Response commentToCommentResponse(Comment comment);
    default CommentDto.Response commentToCommentResponse(Comment comment){
        CommentDto.Response commentResponse = new CommentDto.Response(
                comment.getCommentId(),
                comment.getCommentDetail(),
                comment.getAnswer().getAnswerId(),
                comment.getMemberId()
        );
        return commentResponse;
    }
    List<CommentDto.Response> commentsToCommentResponses(List<Comment> comments);
}
