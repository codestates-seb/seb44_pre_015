package com.mzdevelopers.serverapplication.question.service;

import com.mzdevelopers.serverapplication.question.entity.Question;
import com.mzdevelopers.serverapplication.question.mapper.QuestionMapper;
import com.mzdevelopers.serverapplication.question.repository.QuestionRepository;
import com.mzdevelopers.serverapplication.question.repository.QuestionVoteRepository;
import com.mzdevelopers.serverapplication.question.stub.MemberStubRepository;
import com.mzdevelopers.serverapplication.tag.entity.QuestionTag;
import com.mzdevelopers.serverapplication.tag.entity.Tag;
import com.mzdevelopers.serverapplication.tag.repository.QuestionTagRepository;
import com.mzdevelopers.serverapplication.tag.repository.TagRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestionServiceImplTest {

    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private QuestionTagRepository questionTagRepository;
    @Mock
    private TagRepository tagRepository;
    @Mock
    private MemberStubRepository memberStubRepository;
    @Mock
    private QuestionVoteRepository questionVoteRepository;
    @Mock
    private QuestionMapper questionMapper;
    private QuestionServiceImpl questionService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        questionService = new QuestionServiceImpl(questionRepository,
                questionTagRepository,
                tagRepository,
                questionVoteRepository,
                memberStubRepository,
                questionMapper);
    }

    public Question makeQuestion(String title, String detail, Long memberId) {
        return Question.builder().title(title).detail(detail).memberId(memberId).build();
    }

    @Test
    @DisplayName("질문 생성 태그 미포함 성공")
    void createQuestion_WithoutTags_Success() {
        // Arrange
        Question question = new Question();
        ReflectionTestUtils.setField(question, "questionId", 1L);

        when(questionRepository.save(any(Question.class))).thenReturn(question);

        // Act
        long savedQuestionId = questionService.createQuestion(question, new ArrayList<>());

        // Assert
        assertEquals(question.getQuestionId(), savedQuestionId);
        verify(questionRepository, times(1)).save(question);
        verifyNoMoreInteractions(questionRepository, tagRepository, questionTagRepository);
    }

    @Test
    @DisplayName("질문 생성 태그 포함 성공")
    void createQuestion_WithTags_Success() {
        // Arrange
        Question question = new Question();
        ReflectionTestUtils.setField(question, "questionId", 1L);

        List<Long> tagIds = Arrays.asList(1L, 2L);

        Tag tag1 = new Tag();
        ReflectionTestUtils.setField(tag1, "tagId", 1L);

        Tag tag2 = new Tag();
        ReflectionTestUtils.setField(tag2, "tagId", 2L);

        when(questionRepository.save(any(Question.class))).thenReturn(question);
        when(tagRepository.findById(1L)).thenReturn(Optional.of(tag1));
        when(tagRepository.findById(2L)).thenReturn(Optional.of(tag2));

        // Act
        long savedQuestionId = questionService.createQuestion(question, tagIds);

        // Assert
        assertEquals(question.getQuestionId(), savedQuestionId);
        verify(questionRepository, times(1)).save(question);
        verify(tagRepository, times(1)).findById(1L);
        verify(tagRepository, times(1)).findById(2L);
        verify(questionTagRepository, times(1)).saveAll(anyList());
        verifyNoMoreInteractions(questionRepository, tagRepository, questionTagRepository);
    }

    @Test
    @DisplayName("질문 가져오기 성공")
    void getQuestion_Success() {
        String title = "title";
        String detail = "detail";
        Question question = makeQuestion(title, detail, 1L);
        List<Tag> tags = Arrays.asList(
                new Tag("tag1", "tag1"),
                new Tag("tag2", "tag2")
        );

        List<QuestionTag> questionTags = Arrays.asList(
                new QuestionTag(question, tags.get(0)),
                new QuestionTag(question, tags.get(1))
        );
        ReflectionTestUtils.setField(question, "questionTags", questionTags);

        // when
        when(questionRepository.findById(question.getQuestionId())).thenReturn(Optional.of(question));
        when(questionTagRepository.findByQuestionQuestionId(question.getQuestionId())).thenReturn(questionTags);
        Question findQuestion = questionService.findByQuestionId(question.getQuestionId());

        // then
        assertEquals(findQuestion.getDetail(), detail);
        assertEquals(findQuestion.getTitle(), title);
        assertEquals(findQuestion.getQuestionId(), question.getQuestionId());
        assertEquals(findQuestion.getQuestionTags(), questionTags);
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
        assertThrows(IllegalArgumentException.class, () -> questionService.findByQuestionId(invalidQuestionId));
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
        assertDoesNotThrow(() -> questionService.findByQuestionId(questionId));
        assertEquals(updateDetail, assertQuestion.getDetail());
        assertEquals(updateTitle, assertQuestion.getTitle());
        assertNotEquals(title, saveQuestion.getTitle());
        assertNotEquals(detail, saveQuestion.getDetail());
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
        assertThrows(IllegalArgumentException.class, () -> questionService.findByQuestionId(invalidQuestionId));
    }

    @Test
    @DisplayName("질문 태그 연쇄 삭제 성공")
    void deleteQuestion() {
        // given
        Long questionId = 1L;
        long memberId = 1L;
        Question question = makeQuestion("title", "detail", memberId);

        // when
        when(questionRepository.findById(questionId)).thenReturn(Optional.of(question));
        Question findQuestion = questionService.findByQuestionId(questionId);
        doNothing().when(questionRepository).delete(findQuestion);

        // then
        assertDoesNotThrow(() -> questionService.deleteQuestion(questionId, memberId));
        List<QuestionTag> deletedQuestionTags = questionTagRepository.findByQuestionQuestionId(questionId);
        assertTrue(deletedQuestionTags.isEmpty());
    }

    @Test
    @DisplayName("최신 질문 리스트 반환 성공")
    void recentQuestion() {
        // given
        List<Question> questions = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Question question = makeQuestion(String.valueOf(i), String.valueOf(i) + "번째", 1L);
            ReflectionTestUtils.setField(question, "questionId", (long) i);
            questions.add(question);
        }

        int limit = 10, offset = 0;
        Pageable pageable = PageRequest.of(offset, limit, Sort.by("createdAt").descending());
        Page<Question> questionPage = new PageImpl<>(questions, pageable, questions.size());
        when(questionRepository.findAll(pageable)).thenReturn(questionPage);

        // when
        Page<Question> findQuestionsPage = questionRepository.findAll(pageable);
        List<Question> findQuestions = findQuestionsPage.getContent();

        // then
        for (int i = 1; i < findQuestions.size(); i++) {
            assertEquals((long) i, findQuestions.get(i-1).getQuestionId());
        }
    }

    @Test
    @DisplayName("채택된 질문 리스트 반환 성공")
    void solutionQuestion() {
        // given
        List<Question> questions = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Question question = makeQuestion(String.valueOf(i), String.valueOf(i) + "번째", 1L);
            ReflectionTestUtils.setField(question, "questionId", (long) i);
            if(i % 2 == 0)
                ReflectionTestUtils.setField(question, "solutionStatus", true);
            questions.add(question);
        }

        int limit = 10, offset = 0;
        Pageable pageable = PageRequest.of(offset, limit, Sort.by("createdAt").descending());
        Page<Question> questionPage = new PageImpl<>(questions, pageable, questions.size());

        // when
        when(questionRepository.findBySolutionStatusTrueOrderByCreatedAtDesc(pageable)).thenReturn(questionPage);
        List<Question> findQuestions = questionRepository.findBySolutionStatusTrueOrderByCreatedAtDesc(pageable).getContent();

        // then
        for (int i = 1; i < findQuestions.size(); i++) {
            assertEquals((long) i, findQuestions.get(i-1).getQuestionId());
            if(i % 2 == 0)
                assertTrue(findQuestions.get(i - 1).isSolutionStatus());
            else
                assertFalse(findQuestions.get(i - 1).isSolutionStatus());
        }
    }

    @Test
    @DisplayName("투표 수 기반 질문 리스트 반환 성공")
    void votesQuestion() {
        // given
        List<Question> questions = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Question question = makeQuestion(String.valueOf(i), String.valueOf(i) + "번째", 1L);
            ReflectionTestUtils.setField(question, "questionId", (long) i);
            if(i % 2 == 0)
                ReflectionTestUtils.setField(question, "votesCount", i);
            else
                ReflectionTestUtils.setField(question, "votesCount", 1);
            questions.add(question);
        }

        int limit = 10, offset = 0;
        Pageable pageable = PageRequest.of(offset, limit, Sort.by("voteCount").descending());
        Page<Question> questionPage = new PageImpl<>(questions, pageable, questions.size());

        // when
        when(questionRepository.findAllByOrderByVotesCountDesc(pageable)).thenReturn(questionPage);
        List<Question> findQuestions = questionRepository.findAllByOrderByVotesCountDesc(pageable).getContent();

        // then
        for (int i = 1; i < findQuestions.size(); i++) {
            assertEquals((long) i, findQuestions.get(i-1).getQuestionId());
            if(i % 2 == 0)
                assertEquals(i, findQuestions.get(i-1).getVotesCount());
            else
                assertEquals(1, findQuestions.get(i-1).getVotesCount());
        }
    }


}