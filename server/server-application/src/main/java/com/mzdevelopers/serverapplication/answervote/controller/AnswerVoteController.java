package com.mzdevelopers.serverapplication.answervote.controller;

import com.mzdevelopers.serverapplication.answervote.dto.AnswerVoteDto;
import com.mzdevelopers.serverapplication.answervote.entity.AnswerVote;
import com.mzdevelopers.serverapplication.answervote.mapper.AnswerVoteMapper;
import com.mzdevelopers.serverapplication.answervote.service.AnswerVoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/answers")
public class AnswerVoteController {
    private final AnswerVoteService answerVoteService;

    private final AnswerVoteMapper mapper;

    public AnswerVoteController(AnswerVoteService answerVoteService, AnswerVoteMapper mapper) {
        this.answerVoteService = answerVoteService;
        this.mapper = mapper;
    }

    @PostMapping("/{answer-id}/answer-vote")
    public ResponseEntity postAnswerVote(@PathVariable("answer-id") Long answerId,
                                         @RequestBody AnswerVoteDto.Post requestBody){
        requestBody.setAnswerId(answerId);
        AnswerVote answerVote = answerVoteService.createAnswerVote(mapper.postDtoToAnswerVote(requestBody));

        return new ResponseEntity(mapper.answerVoteToAnswerVoteDtoResponse(answerVote), HttpStatus.CREATED);
    }

    @DeleteMapping("/{answer-vote-id}/answer-vote")
    public ResponseEntity deleteAnswerVote(@PathVariable("answer-vote-id") Long answerVoteId){
        answerVoteService.deleteAnswerVote(answerVoteId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
