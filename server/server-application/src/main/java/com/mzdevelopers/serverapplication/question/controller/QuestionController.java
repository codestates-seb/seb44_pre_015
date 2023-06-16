package com.mzdevelopers.serverapplication.question.controller;

import com.mzdevelopers.serverapplication.question.dto.QuestionPatchDto;
import com.mzdevelopers.serverapplication.question.dto.QuestionRequestDto;
import com.mzdevelopers.serverapplication.question.dto.QuestionResponseDto;
import com.mzdevelopers.serverapplication.question.dto.StubAnswer;
import com.mzdevelopers.serverapplication.question.entity.Question;
import com.mzdevelopers.serverapplication.question.mapper.QuestionMapper;
import com.mzdevelopers.serverapplication.question.service.QuestionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionMapper questionMapper;
    private final QuestionServiceImpl questionService;
    private final static String QUESTION_CREATE_URI = "http://localhost:8080/questions";

    @PostMapping("/register")
    public ResponseEntity<?> postQuestion(@Valid @RequestBody QuestionRequestDto questionRequestDto) {
        long questionId = questionService.creatQuestion(
                questionMapper.questionRequestDtoToQuestion(questionRequestDto), questionRequestDto.getTags()
        );
        URI location = questionService.uriBuilder(questionId, QUESTION_CREATE_URI);

        return ResponseEntity.created(location).build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> getQuestion(@PathVariable("id") long questionId) {
        QuestionResponseDto responseDto = questionMapper
                .questionToQuestionResponseDto(questionService.getQuestion(questionId));

        responseDto.setAnswers(questionService.stubAnswerList());
//        responseDto.setTags(questionService.findByTagId());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> patchQuestion(@PathVariable("id") long questionId,
                                                             @Valid @RequestBody QuestionPatchDto questionPatchDto) {
        QuestionResponseDto responseDto = questionMapper.questionToQuestionResponseDto(questionService
                .updateQuestion(questionId,
                        questionPatchDto.getTitle(),
                        questionPatchDto.getDetail(),
                        questionPatchDto.getMemberId()));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") long questionId) {
        questionService.deleteQuestion(questionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
