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
    Comment commentPostToComment(CommentDto.Post requestBody);
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
