package com.mzdevelopers.serverapplication.question.repository;

import com.mzdevelopers.serverapplication.question.entity.Question;
import com.mzdevelopers.serverapplication.question.entity.QuestionVote;
import com.mzdevelopers.serverapplication.question.stub.MemberStub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionVoteRepository extends JpaRepository<QuestionVote, Long> {
    Optional<QuestionVote> findByQuestionAndMemberStub(Question question, MemberStub memberStub);
}

