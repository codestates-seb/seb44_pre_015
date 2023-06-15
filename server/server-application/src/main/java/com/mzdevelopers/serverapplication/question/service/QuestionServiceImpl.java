package com.mzdevelopers.serverapplication.question.service;

import com.mzdevelopers.serverapplication.question.dto.StubAnswer;
import com.mzdevelopers.serverapplication.question.entity.Question;
import com.mzdevelopers.serverapplication.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{
    private final QuestionRepository questionRepository;

    @Override
    public long creatQuestion(Question question) {
        Question saveQuestion = questionRepository.save(question);
        return saveQuestion.getQuestionId();
    }

    @Override
    public Question getQuestion(long questionId) {
        return findByQuestionId(questionId);
    }

    @Override
    public void updateQuestion(long questionId, String title, String detail, long memberId) {
        Question findQuestion = findByQuestionId(questionId);
        if (findQuestion.getMemberId() == memberId) {
            findQuestion.update(title, detail);
            questionRepository.save(findQuestion);
        } else {
            throw new IllegalArgumentException("수정할 권한이 없습니다: " + memberId);
        }
    }

    @Override
    public void deleteQuestion(long questionId) {
        Question findQuestion = findByQuestionId(questionId);
        questionRepository.delete(findQuestion);
    }

    public Question findByQuestionId(Long questionId) {
        Optional<Question> findQuestion = questionRepository.findById(questionId);
        return findQuestion.orElseThrow(() -> new IllegalArgumentException("No Search Question: " + questionId));
    }

    public URI uriBuilder(long questionId, String basicURL) {
        return UriComponentsBuilder
                .fromUriString(basicURL)
                .path("/"+String.valueOf(questionId))
                .build()
                .toUri();
    }


    // stub data zone
    public List<StubAnswer> stubAnswerList() {
        StubAnswer stubAnswer = new StubAnswer();
        stubAnswer.setTitle("title");
        stubAnswer.setDetail("detail");
        return List.of(stubAnswer);
    }



}
