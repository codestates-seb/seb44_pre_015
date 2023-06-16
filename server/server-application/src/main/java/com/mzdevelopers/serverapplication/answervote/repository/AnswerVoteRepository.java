package com.mzdevelopers.serverapplication.answervote.repository;

import com.mzdevelopers.serverapplication.answervote.entity.AnswerVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerVoteRepository extends JpaRepository<AnswerVote, Long> {
}
