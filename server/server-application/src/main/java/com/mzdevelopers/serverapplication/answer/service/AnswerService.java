package com.mzdevelopers.serverapplication.answer.service;

import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.answer.repository.AnswerRepository;
import com.mzdevelopers.serverapplication.answervote.entity.AnswerVote;
import com.mzdevelopers.serverapplication.answervote.repository.AnswerVoteRepository;
import com.mzdevelopers.serverapplication.question.entity.Question;
import com.mzdevelopers.serverapplication.question.entity.QuestionVote;
import com.mzdevelopers.serverapplication.question.repository.QuestionRepository;
import com.mzdevelopers.serverapplication.question.stub.MemberStub;
import com.mzdevelopers.serverapplication.question.stub.MemberStubRepository;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final AnswerVoteRepository answerVoteRepository;
    private final MemberStubRepository memberStubRepository;



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


    public int votesCount(long answerId, long memberId) {
        Answer findAnswer = findByAnswerId(answerId);
        MemberStub memberStub = save();
        Optional<AnswerVote> optionalAnswerVote = answerVoteRepository.findByAnswerAndMemberStub(findAnswer, memberStub);
        if (optionalAnswerVote.isEmpty()) {
            AnswerVote answerVote = AnswerVote.builder().answer(findAnswer).memberStub(memberStub).build();
            findAnswer.updateVoteCount(true);
            answerVoteRepository.save(answerVote);
        } else {
            AnswerVote findAnswerVote = optionalAnswerVote.get();
            findAnswerVote.updateVote();
            answerVoteRepository.save(findAnswerVote);
            findAnswer.updateVoteCount(findAnswerVote.isAnswerVoted());
        }
        Answer updatedAnswer = answerRepository.save(findAnswer);
        return updatedAnswer.getVotesCount();
    }

    public Answer findByAnswerId(Long answerId) {
        Optional<Answer> findAnswer = answerRepository.findById(answerId);
        return findAnswer.orElseThrow(() -> new IllegalArgumentException("No Search Question: " + answerId));
    }

    public MemberStub save(){
        MemberStub memberStub = MemberStub.builder().name("member").build();
        memberStubRepository.save(memberStub);
        return memberStubRepository.findById(1L).orElseGet(null);
    }
}
