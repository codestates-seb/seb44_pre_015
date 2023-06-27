package com.mzdevelopers.serverapplication.answer.service;

import com.mzdevelopers.serverapplication.answer.dto.AnswerVoteCountDto;
import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.answer.repository.AnswerRepository;
import com.mzdevelopers.serverapplication.answervote.entity.AnswerVote;
import com.mzdevelopers.serverapplication.answervote.repository.AnswerVoteRepository;
import com.mzdevelopers.serverapplication.exception.BusinessLogicException;
import com.mzdevelopers.serverapplication.exception.ExceptionCode;
import com.mzdevelopers.serverapplication.member.entity.Member;
import com.mzdevelopers.serverapplication.member.repository.MemberRepository;
import com.mzdevelopers.serverapplication.question.entity.Question;
import com.mzdevelopers.serverapplication.question.repository.QuestionRepository;
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
    private final MemberRepository memberRepository;



    public Answer createAnswer(Answer answer){
        Question findQuestion = questionRepository.findByQuestionId(answer.getQuestion().getQuestionId())
                .orElseThrow(()->new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
        Member findMember = memberRepository.findById(answer.getMember().getMemberId())
                .orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        answer.setMember(findMember);

        Answer saveAnswer = answerRepository.save(answer);
        findQuestion.getAnswers().add(saveAnswer);
        questionRepository.save(findQuestion);
        return saveAnswer;
    }


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Answer updateAnswer(String detail, long answerId, long memberId){
        Answer findAnswer = findVerifiedAnswer(answerId);

        if(findAnswer.getMember().getMemberId()==memberId){
            findAnswer.update(detail);
            return answerRepository.saveAndFlush(findAnswer);
        }
        else{throw new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_ANSWER);}

    }


    @Transactional(readOnly = true)
    public Answer findAnswer(long answerId){

        return answerRepository.findById(answerId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
    }


    public Page<Answer> findAnswers(int page, int size) {
        return answerRepository.findAll(PageRequest.of(page, size,
                Sort.by("answerId").descending()));
    }
    public void deleteAnswer(long answerId, long memberId) {
        Answer findAnswer = findVerifiedAnswer(answerId);
        Member findMember = memberRepository.findById(memberId).orElseThrow(()->new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        if(findAnswer.getMember().getMemberId()!=findMember.getMemberId()){
            throw new BusinessLogicException(ExceptionCode.CANNOT_DELETE_ANSWER);
        }


        answerRepository.delete(findAnswer);
    }


    @Transactional(readOnly = true)
    public Answer findVerifiedAnswer(long answerId){
        Optional<Answer> optionalAnswer =
                answerRepository.findByAnswerId(answerId);
        return optionalAnswer.orElseThrow(() -> new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
    }


    public AnswerVoteCountDto votesCount(long answerId, long memberId) {
        Answer findAnswer = findByAnswerId(answerId);
        Member findMember = findByMemberId(memberId);
        Optional<AnswerVote> optionalAnswerVote = answerVoteRepository.findByAnswerAndMember(findAnswer, findMember);
        AnswerVote saveAnswerVote;

        if (optionalAnswerVote.isEmpty()) {
            AnswerVote answerVote = AnswerVote.builder().answer(findAnswer).member(findMember).build();
            findAnswer.updateVoteCount(true);
            saveAnswerVote = answerVoteRepository.saveAndFlush(answerVote);
        } else {
            AnswerVote findAnswerVote = optionalAnswerVote.get();
            findAnswerVote.updateVote();
            saveAnswerVote =answerVoteRepository.saveAndFlush(findAnswerVote);
            findAnswer.updateVoteCount(findAnswerVote.isAnswerVoted());//
        }
        Answer updatedAnswer = answerRepository.saveAndFlush(findAnswer);

        AnswerVoteCountDto answerVoteCountDto = new AnswerVoteCountDto();
        answerVoteCountDto.setTotalVoteCount(updatedAnswer.getVotesCount());
        answerVoteCountDto.setAnswerVoteStatus(saveAnswerVote.isAnswerVoted());
        return answerVoteCountDto;
    }

    public Answer findByAnswerId(Long answerId) {
        Optional<Answer> findAnswer = answerRepository.findById(answerId);
        return findAnswer.orElseThrow(() -> new IllegalArgumentException("No Search Question: " + answerId));
    }

    public Member findByMemberId(Long memberId){
        Optional<Member> findMember = memberRepository.findById(memberId);
        return findMember.orElseThrow(() -> new IllegalArgumentException("No Search Member: " + memberId));
    }

    public Boolean updateSelection(long answerId, long memberId){

        Answer findAnswer = findVerifiedAnswer(answerId);


        if(findAnswer.getQuestion().getMember().getMemberId() == memberId){
            boolean solutionStatus=findAnswer.isSolutionStatus();
            findAnswer.updateSelect(!solutionStatus);

            Question findQuestion = questionRepository.findById(findAnswer.getQuestion().getQuestionId())
                    .orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));

            findQuestion.updateSelect(findAnswer.isSolutionStatus());
            answerRepository.saveAndFlush(findAnswer);
            questionRepository.saveAndFlush(findQuestion);
        }
        else{
            throw new BusinessLogicException(ExceptionCode.MEMBER_NO_PERMISSION);
        }
        return findAnswer.isSolutionStatus();
    }
}
