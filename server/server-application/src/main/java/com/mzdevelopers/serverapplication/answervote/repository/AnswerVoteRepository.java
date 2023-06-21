package com.mzdevelopers.serverapplication.answervote.repository;

import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.answervote.entity.AnswerVote;
import com.mzdevelopers.serverapplication.question.stub.MemberStub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {
    Optional<AnswerVote> findByAnswerAndMemberStub(Answer answer, MemberStub memberStub);
}
