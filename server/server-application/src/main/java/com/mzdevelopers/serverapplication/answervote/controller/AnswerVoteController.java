package com.mzdevelopers.serverapplication.answervote.controller;

import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.answer.service.AnswerService;
import com.mzdevelopers.serverapplication.answervote.dto.AnswerVoteDto;
import com.mzdevelopers.serverapplication.answervote.entity.AnswerVote;
import com.mzdevelopers.serverapplication.answervote.mapper.AnswerVoteMapper;
import com.mzdevelopers.serverapplication.answervote.service.AnswerVoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/answers")
@Validated
public class AnswerVoteController {
    private final AnswerVoteService answerVoteService;

    private final AnswerService answerService;

    private final AnswerVoteMapper mapper;

    public AnswerVoteController(AnswerVoteService answerVoteService,
                                AnswerService answerService,
                                AnswerVoteMapper mapper) {
        this.answerVoteService = answerVoteService;
        this.answerService = answerService;
        this.mapper = mapper;
    }

    @PostMapping("/{answer-id}/answer-vote")
    public ResponseEntity postAnswerVote(@PathVariable("answer-id") Long answerId,
                                         @Valid @RequestBody AnswerVoteDto.Post requestBody){
        requestBody.setAnswerId(answerId);
        Answer answer = answerService.findAnswer(answerId);
        AnswerVote answerVote = answerVoteService.createAnswerVote(mapper.postDtoToAnswerVote(requestBody),answer);

        return new ResponseEntity(mapper.answerVoteToAnswerVoteDtoResponse(answerVote), HttpStatus.CREATED);
//        return new ResponseEntity(answerVote, HttpStatus.CREATED);
    }

    @DeleteMapping("/{answer-vote-id}/answer-vote")
    public ResponseEntity deleteAnswerVote(@PathVariable("answer-vote-id") Long answerVoteId){
        answerVoteService.deleteAnswerVote(answerVoteId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
