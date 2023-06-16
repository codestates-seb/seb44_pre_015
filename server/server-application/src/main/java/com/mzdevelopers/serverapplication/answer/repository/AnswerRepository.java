package com.mzdevelopers.serverapplication.answer.repository;

import com.mzdevelopers.serverapplication.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
