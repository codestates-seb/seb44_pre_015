package com.mzdevelopers.serverapplication.answervote.service;

import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.answer.service.AnswerService;
import com.mzdevelopers.serverapplication.answervote.entity.AnswerVote;
import com.mzdevelopers.serverapplication.answervote.repository.AnswerVoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class AnswerVoteService {
    private final AnswerVoteRepository answerVoteRepository;

    private final AnswerService answerService;


    public AnswerVoteService(AnswerVoteRepository answerVoteRepository, AnswerService answerService) {
        this.answerVoteRepository = answerVoteRepository;
        this.answerService = answerService;
    }

    public AnswerVote createAnswerVote(AnswerVote answerVote) {
        verifyAnswerVote(answerVote);
        return answerVoteRepository.save(answerVote);
    }

    public void deleteAnswerVote(long answerVoteId) {
        AnswerVote findAnswerVote = findVerifiedAnswerVote(answerVoteId);
        answerVoteRepository.delete(findAnswerVote);
    }

    public AnswerVote findVerifiedAnswerVote(long answerVoteId){
        Optional<AnswerVote> optionalAnswerVote = answerVoteRepository.findById(answerVoteId);
        AnswerVote findAnswerVote = optionalAnswerVote.orElseThrow(() ->new RuntimeException("NOT FOUND"));
        return findAnswerVote;
    }

    private void verifyAnswerVote(AnswerVote answerVote){
        answerService.findVerifiedAnswer(answerVote.getAnswer().getAnswerId());
    }
}