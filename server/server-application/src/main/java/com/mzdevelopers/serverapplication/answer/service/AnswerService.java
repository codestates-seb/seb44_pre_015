package com.mzdevelopers.serverapplication.answer.service;

import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.answer.repository.AnswerRepository;
import com.mzdevelopers.serverapplication.question.entity.Question;
import com.mzdevelopers.serverapplication.question.repository.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    public Answer createAnswer(Answer answer){
        Question findQuestion = questionRepository.findByQuestionId(answer.getQuestion().getQuestionId());

        Answer saveAnswer = answerRepository.save(answer);
        findQuestion.getAnswers().add(saveAnswer);
        questionRepository.save(findQuestion);
        return saveAnswer;
    }


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Answer updateAnswer(Answer answer){
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());

        Optional.ofNullable(answer.getDetail())
                .ifPresent(detail -> findAnswer.setDetail(detail));
        Optional.ofNullable(answer.isSolutionStatus())
                .ifPresent(solutionStatus -> findAnswer.setSolutionStatus(solutionStatus));

        return answerRepository.save(findAnswer);
    }


    @Transactional(readOnly = true)
    public Answer findAnswer(long answerId){

        return answerRepository.findByAnswerId(answerId);
    }


    public Page<Answer> findAnswers(int page, int size) {
        return answerRepository.findAll(PageRequest.of(page, size,
                Sort.by("answerId").descending()));
    }
    public void deleteAnswer(long answerId) {
        Answer findAnswer = findVerifiedAnswer(answerId);

        answerRepository.delete(findAnswer);
    }


    @Transactional(readOnly = true)
    public Answer findVerifiedAnswer(long answerId){
        Optional<Answer> optionalAnswer =
                Optional.ofNullable(answerRepository.findByAnswerId(answerId));
        return optionalAnswer.orElseThrow();
    }

}
