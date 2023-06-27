package com.mzdevelopers.serverapplication.answer.controller;

import com.mzdevelopers.serverapplication.answer.dto.AnswerDto;
import com.mzdevelopers.serverapplication.answer.dto.AnswerVoteCountDto;
import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.answer.mapper.AnswerMapper;
import com.mzdevelopers.serverapplication.answer.service.AnswerService;
import com.mzdevelopers.serverapplication.answer.pagedto.MultiResponseDto;
import com.mzdevelopers.serverapplication.question.dto.QuestionVoteCountDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/answers")
@Validated
@Slf4j
public class AnswerController {
    @Autowired
    private final AnswerService answerService;

    private final AnswerMapper mapper;

    @Autowired
    public AnswerController(AnswerService answerService, AnswerMapper mapper) {
        this.answerService = answerService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postAnswer(@Valid @RequestBody AnswerDto.Post requestBody){
        Answer createAnswer = answerService.createAnswer(mapper.answerPostToAnswer(requestBody));

        return new ResponseEntity(mapper.answerToAnswerResponse(createAnswer),HttpStatus.CREATED);
    }

    @PatchMapping("/{answer-id}/{member-id}")
    public ResponseEntity patchAnswer(@PathVariable("answer-id")@Positive long answerId,
                                      @PathVariable("member-id")@Positive long memberId,
                                      @Valid@RequestBody AnswerDto.Patch requestBody){

        Answer answer = answerService.updateAnswer(requestBody.getDetail(),answerId,memberId);
        return new ResponseEntity<>(mapper.answerToAnswerResponse(answer), HttpStatus.OK);
    }

    @GetMapping("/{answer-id}")
    public ResponseEntity getAnswer(
            @PathVariable("answer-id") @Positive long answerId) {
        Answer answer = answerService.findAnswer(answerId);
        AnswerDto.Response response = mapper.answerToAnswerResponse(answer);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity getAnswers(@Positive @RequestParam int page,
//                                     @Positive @RequestParam int size) {
//        Page<Answer> pageAnswers = answerService.findAnswers(page - 1, size);
//        List<Answer> answers = pageAnswers.getContent();
//        return new ResponseEntity<>(
//                new MultiResponseDto<>(mapper.answersToAnswerResponses(answers),
//                        pageAnswers),
//                HttpStatus.OK);
//    }

    @DeleteMapping("/{answer-id}/{member-id}")
    public ResponseEntity deleteAnswer(
            @PathVariable("answer-id") @Positive long answerId,
            @PathVariable("member-id") long memberId) {
        answerService.deleteAnswer(answerId, memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/votes/{answerId}/{memberId}")
    public ResponseEntity<?> votesCount(@PathVariable("answerId") Long answerId,
                                        @PathVariable Long memberId) {
        AnswerVoteCountDto response = answerService.votesCount(answerId, memberId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/selection")
    public ResponseEntity<?> selectAnswer(@RequestParam Long answerId,
                                          @RequestParam Long memberId) {

        boolean selectBool = answerService.updateSelection(answerId, memberId);
        return new ResponseEntity<>(selectBool, HttpStatus.OK);
    }
}
