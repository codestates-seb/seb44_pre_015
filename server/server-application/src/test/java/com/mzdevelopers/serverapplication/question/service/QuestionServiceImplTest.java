package com.mzdevelopers.serverapplication.question.service;

import com.mzdevelopers.serverapplication.question.entity.Question;
import com.mzdevelopers.serverapplication.question.repository.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

class QuestionServiceImplTest {

    @Mock
    private QuestionRepository questionRepository;

    private QuestionServiceImpl questionService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        questionService = new QuestionServiceImpl(questionRepository);
    }

    public Question makeQuestion(String title, String detail, Long memberId) {
        return Question.builder().title(title).detail(detail).memberId(memberId).build();
    }

    @Test
    @DisplayName("질문 생성 성공")
    void creatQuestion_Success() {
        // given
        String title = "이거 너무 어려워요";
        String detail = "간단한거지만 너무 어렵에요";
        Question question = makeQuestion(title, detail, 1L);

        // when
        when(questionRepository.save(question)).thenReturn(question);

        // then
        Question saveQuestion = questionRepository.save(question);
        Assertions.assertNull(saveQuestion.getQuestionId());
        Assertions.assertEquals(title, saveQuestion.getTitle());
        Assertions.assertEquals(detail, saveQuestion.getDetail());
    }

    @Test
    @DisplayName("질문 가져오기 성공")
    void getQuestion_Success() {
        String title = "title";
        String detail = "detail";
        Question question = makeQuestion(title, detail, 1L);

        // when
        when(questionRepository.findById(question.getQuestionId())).thenReturn(Optional.of(question));
        Question findQuestion = questionService.findByQuestionId(question.getQuestionId());

        // then
        Assertions.assertEquals(findQuestion.getDetail(), detail);
        Assertions.assertEquals(findQuestion.getTitle(), title);
        Assertions.assertEquals(findQuestion.getQuestionId(), question.getQuestionId());
    }

    @Test
    @DisplayName("질문 가져오기 실패 - 잘못된 질문 ID")
    void getQuestion_Failure(){
        // given
        Long invalidQuestionId = 2L;

        // when
        when(questionRepository.findById(invalidQuestionId)).thenReturn(Optional.empty());

        // then
//        Question question = questionService.findByQuestionId(invalidQuestionId);
        Assertions.assertThrows(IllegalArgumentException.class, () -> questionService.findByQuestionId(invalidQuestionId));
    }

    @Test
    @DisplayName("질문 수정 성공")
    void updateQuestion_Success() {
        // given
        Long questionId = 1L;
        String title = "title";
        String detail = "detail";
        String updateTitle = "title2";
        String updateDetail = "detail2";
        Question save = makeQuestion(title, detail, 1L);

        // when
        when(questionRepository.save(save)).thenReturn(save);
        Question saveQuestion = questionRepository.save(save);

        when(questionRepository.findById(questionId)).thenReturn(Optional.of(saveQuestion));
        Question findQuestion = questionService.findByQuestionId(questionId);


        findQuestion.update(updateTitle, updateDetail);
        when(questionRepository.save(findQuestion)).thenReturn(findQuestion);

        // then
        Question assertQuestion = questionService.findByQuestionId(questionId);
        Assertions.assertDoesNotThrow(() -> questionService.findByQuestionId(questionId));
        Assertions.assertEquals(updateDetail, assertQuestion.getDetail());
        Assertions.assertEquals(updateTitle, assertQuestion.getTitle());
        Assertions.assertNotEquals(title, saveQuestion.getTitle());
        Assertions.assertNotEquals(detail, saveQuestion.getDetail());
    }

    @Test
    @DisplayName("질문 수정 실패 - 잘못된 질문 ID")
    void updateQuestion_Failure_Invalid_Id() {
        // given
        Long invalidQuestionId = 2L;
        String title = "title";
        String detail = "detail";
        Question save = makeQuestion(title, detail, 1L);

        // when
        when(questionRepository.save(save)).thenReturn(save);
        questionRepository.save(save);

        when(questionRepository.findById(invalidQuestionId)).thenReturn(Optional.empty());

        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> questionService.findByQuestionId(invalidQuestionId));
    }

    @Test
    @DisplayName("질문 삭제 성공")
    void deleteQuestion() {
        // given
        Long questionId = 1L;
        Question question = makeQuestion("title", "detail", 1L);

        // when
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(question));
        Question findQuestion = questionService.findByQuestionId(questionId);
        doNothing().when(questionRepository).delete(findQuestion);

        // then
        Assertions.assertDoesNotThrow(() -> questionService.deleteQuestion(questionId));
    }


}