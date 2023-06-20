package com.mzdevelopers.serverapplication.question.repository;

import com.mzdevelopers.serverapplication.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
