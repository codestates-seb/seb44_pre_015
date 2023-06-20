package com.mzdevelopers.serverapplication.comment.controller;

import com.mzdevelopers.serverapplication.answer.dto.AnswerDto;
import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.answer.pagedto.MultiResponseDto;
import com.mzdevelopers.serverapplication.answer.service.AnswerService;
import com.mzdevelopers.serverapplication.comment.dto.CommentDto;
import com.mzdevelopers.serverapplication.comment.entity.Comment;
import com.mzdevelopers.serverapplication.comment.mapper.CommentMapper;
import com.mzdevelopers.serverapplication.comment.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/comments")
@Validated
@Slf4j
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper mapper;

    public CommentController(CommentService commentService, CommentMapper mapper) {
        this.commentService = commentService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postComment(@Valid @RequestBody CommentDto.Post requestBody){
        Comment createComment = commentService.createComment(mapper.commentPostToComment(requestBody));

        return new ResponseEntity(mapper.commentToCommentResponse(createComment), HttpStatus.CREATED);
//        return new ResponseEntity(createComment, HttpStatus.CREATED);
    }

    @PatchMapping("/{comment-id}")
    public ResponseEntity patchComment(@PathVariable("comment-id")@Positive long commentId,
                                      @Valid@RequestBody CommentDto.Patch requestBody){
        requestBody.setCommentId(commentId);
        Comment comment = commentService.updateComment(mapper.commentPatchToComment(requestBody));
        return new ResponseEntity<>(mapper.commentToCommentResponse(comment), HttpStatus.OK);
    }

    @GetMapping("/{comment-id}")
    public ResponseEntity getComment(
            @PathVariable("comment-id") @Positive long commentId) {
        Comment comment = commentService.findComment(commentId);
        return new ResponseEntity<>(mapper.commentToCommentResponse(comment), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getComments(@Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {
        Page<Comment> pageComments = commentService.findComments(page - 1, size);
        List<Comment> comments = pageComments.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(mapper.commentsToCommentResponses(comments),
                        pageComments),
                HttpStatus.OK);
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteComment(
            @PathVariable("comment-id") @Positive long commentId) {
        commentService.deleteComment(commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
