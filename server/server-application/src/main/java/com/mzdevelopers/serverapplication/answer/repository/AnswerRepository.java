package com.mzdevelopers.serverapplication.answer.repository;

import com.mzdevelopers.serverapplication.answer.entity.Answer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @EntityGraph(attributePaths = "comments")
    Optional<Answer> findByAnswerId(long answerId);
}
