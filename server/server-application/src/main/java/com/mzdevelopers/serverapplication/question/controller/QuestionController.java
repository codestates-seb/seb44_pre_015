package com.mzdevelopers.serverapplication.question.controller;

import com.mzdevelopers.serverapplication.question.dto.QuestionPatchDto;
import com.mzdevelopers.serverapplication.question.dto.QuestionRequestDto;
import com.mzdevelopers.serverapplication.question.dto.QuestionResponseDto;
import com.mzdevelopers.serverapplication.question.dto.QuestionVoteCountDto;
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
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionMapper questionMapper;
    private final QuestionServiceImpl questionService;
    private final static String QUESTION_CREATE_URI = "http://localhost:8080/questions";

    // 질문 등록
    @PostMapping("/register")
    public ResponseEntity<?> postQuestion(@Valid @RequestBody QuestionRequestDto questionRequestDto) {
        long questionId = questionService.createQuestion(
                questionMapper.questionRequestDtoToQuestion(questionRequestDto), questionRequestDto.getTags()
        );
        URI location = questionService.uriBuilder(questionId, QUESTION_CREATE_URI);

        return ResponseEntity.created(location).build();
    }

    // 질문 가져오기 + 애플리케이션 사용자이면 view 증가
    @GetMapping("/{questionId}/{memberId}")
    public ResponseEntity<QuestionResponseDto> getQuestion(@PathVariable("questionId") long questionId,
                                                           @PathVariable long memberId) {
        QuestionResponseDto findQuestion = questionService.getQuestion(questionId, memberId);
        return ResponseEntity.ok(findQuestion);
    }

    // 질문 수정
    @PatchMapping("/{questionId}/{memberId}")
    public ResponseEntity<QuestionResponseDto> patchQuestion(@PathVariable("questionId") long questionId,
                                                             @PathVariable("memberId") long memberId,
                                                             @Valid @RequestBody QuestionPatchDto questionPatchDto) {
        QuestionResponseDto responseDto = questionMapper.questionToQuestionResponseDto(questionService
                .updateQuestion(questionId,
                        questionPatchDto.getTitle(),
                        questionPatchDto.getDetail(),
                        memberId));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 질문 삭제
    @DeleteMapping("/{questionId}/{memberId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable("questionId") Long questionId,
                                            @PathVariable Long memberId) {
        questionService.deleteQuestion(questionId, memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 좋아요 누르기
    @GetMapping("/votes/{questionId}/{memberId}")
    public ResponseEntity<?> votesCount(@PathVariable("questionId") Long questionId,
                                        @PathVariable Long memberId) {
        QuestionVoteCountDto response = new QuestionVoteCountDto();
        response.setTotalVoteCount(questionService.votesCount(questionId, memberId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // api 별 질문 리스트 반환
    @CrossOrigin
    @GetMapping("/{api}")
    public ResponseEntity<?> getQuestionsByAPI(@PathVariable("api") String api,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {
        List<Question> questions = questionService.questionsListByAPI(page, size, api);
        List<QuestionResponseDto> responseDtoList = new ArrayList<>();
        for (Question question : questions) {
            QuestionResponseDto dto = questionService.getQuestion(question.getQuestionId(), question.getMemberId());
            responseDtoList.add(dto);
        }
        return ResponseEntity.ok(responseDtoList);
    }


}
