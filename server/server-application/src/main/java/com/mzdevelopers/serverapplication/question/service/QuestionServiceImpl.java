package com.mzdevelopers.serverapplication.question.service;

import com.mzdevelopers.serverapplication.question.dto.StubAnswer;
import com.mzdevelopers.serverapplication.question.entity.Question;
import com.mzdevelopers.serverapplication.question.repository.QuestionRepository;
import com.mzdevelopers.serverapplication.tag.entity.QuestionTag;
import com.mzdevelopers.serverapplication.tag.entity.Tag;
import com.mzdevelopers.serverapplication.tag.repository.QuestionTagRepository;
import com.mzdevelopers.serverapplication.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{
    private final QuestionRepository questionRepository;
    private final QuestionTagRepository questionTagRepository;
    private final TagRepository tagRepository;

    @Override
    public long creatQuestion(Question question, List<Long> tags) {
        Question savedQuestion = questionRepository.save(question);

        if (!tags.isEmpty()) {
            List<Tag> tagList = findByTagId(tags);
            List<QuestionTag> questionTags = new ArrayList<>();
            for (Tag tag : tagList) {
                QuestionTag questionTag = new QuestionTag(savedQuestion, tag);
                questionTags.add(questionTag);
            }
            questionTagRepository.saveAll(questionTags);
        }

        return savedQuestion.getQuestionId();
    }

    @Override
    public Question getQuestion(long questionId) {
        Question findQuestion = findByQuestionId(questionId);
        System.out.println("getQuestion : " + findQuestion.getQuestionTags().get(0).toString());
        return findQuestion;
    }

    @Override
    public Question updateQuestion(long questionId, String title, String detail, long memberId) {
        Question findQuestion = findByQuestionId(questionId);
        if (findQuestion.getMemberId() == memberId) {
            findQuestion.update(title, detail);
            return questionRepository.save(findQuestion);
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

    public List<Tag> findByTagId(List<Long> tagIds) {
        List<Tag> tagList = new ArrayList<>();
        for (Long id : tagIds) {
            Tag tag = tagRepository.findById(id).orElseGet(() -> null);
            tagList.add(tag);
        }
        return tagList;
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
