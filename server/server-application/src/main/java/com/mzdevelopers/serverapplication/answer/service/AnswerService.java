package com.mzdevelopers.serverapplication.answer.service;

import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.answer.repository.AnswerRepository;
import com.mzdevelopers.serverapplication.answervote.entity.AnswerVote;
import com.mzdevelopers.serverapplication.answervote.repository.AnswerVoteRepository;
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
        Question findQuestion = questionRepository.findByQuestionId(answer.getQuestion().getQuestionId());
        Member findMember = memberRepository.findById(answer.getMember().getMemberId()).orElseThrow(()->new RuntimeException("멤버를 찾을 수 없습니다."));
        answer.setMember(findMember);

        Answer saveAnswer = answerRepository.save(answer);
        findQuestion.getAnswers().add(saveAnswer);
        questionRepository.save(findQuestion);
        return saveAnswer;
    }


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Answer updateAnswer(Answer answer){
        Answer findAnswer = findVerifiedAnswer(answer.getAnswerId());
        Member findMember = memberRepository.findById(answer.getMember().getMemberId()).orElseThrow(()->new RuntimeException("멤버를 찾을 수 없습니다."));

        if(findAnswer.getMember().getMemberId()!=findMember.getMemberId()){
            throw new RuntimeException("수정할 권한이 없습니다.");
        }

        Optional.ofNullable(answer.getDetail())
                .ifPresent(detail -> findAnswer.setDetail(detail));
        Optional.ofNullable(answer.isSolutionStatus())
                .ifPresent(solutionStatus -> findAnswer.setSolutionStatus(solutionStatus));

        return answerRepository.save(findAnswer);
    }


    @Transactional(readOnly = true)
    public Answer findAnswer(long answerId){

        return answerRepository.findByAnswerId(answerId).orElseGet(null);
    }


    public Page<Answer> findAnswers(int page, int size) {
        return answerRepository.findAll(PageRequest.of(page, size,
                Sort.by("answerId").descending()));
    }
    public void deleteAnswer(long answerId, long memberId) {
        Answer findAnswer = findVerifiedAnswer(answerId);
        Member findMember = memberRepository.findById(memberId).orElseThrow(()->new RuntimeException("멤버를 찾을 수 없습니다."));

        if(findAnswer.getMember().getMemberId()!=findMember.getMemberId()){
            throw new RuntimeException("삭제할 권한이 없습니다.");
        }


        answerRepository.delete(findAnswer);
    }


    @Transactional(readOnly = true)
    public Answer findVerifiedAnswer(long answerId){
        Optional<Answer> optionalAnswer =
                answerRepository.findByAnswerId(answerId);
        return optionalAnswer.orElseThrow();
    }


    public int votesCount(long answerId, long memberId) {
        Answer findAnswer = findByAnswerId(answerId);
        Member findMember = findByMemberId(memberId);
        Optional<AnswerVote> optionalAnswerVote = answerVoteRepository.findByAnswerAndMember(findAnswer, findMember);
        if (optionalAnswerVote.isEmpty()) {
            AnswerVote answerVote = AnswerVote.builder().answer(findAnswer).member(findMember).build();
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

    public Member findByMemberId(Long memberId){
        Optional<Member> findMember = memberRepository.findById(memberId);
        return findMember.orElseThrow(() -> new IllegalArgumentException("No Search Member: " + memberId));
    }
}
