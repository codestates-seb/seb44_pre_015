package com.mzdevelopers.serverapplication.question.repository;

import com.mzdevelopers.serverapplication.answer.entity.Answer;
import com.mzdevelopers.serverapplication.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
//    @Query(value = "SELECT * FROM question WHERE solution_status = true ORDER BY created_at DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
//    List<Question> findQuestionsBySolutionStatusWithLimitOffset(@Param("limit") int limit, @Param("offset") int offset);
//
//    @Query("SELECT q FROM Question q ORDER BY q.createdAt DESC")
//    List<Question> findQuestionsByCreatedAtDesc();

    Page<Question> findBySolutionStatusTrueOrderByCreatedAtDesc(Pageable pageable);

    Page<Question> findAllByOrderByVotesCountDesc(Pageable pageable);

    @EntityGraph(attributePaths = "answers")
    Question findByQuestionId(long questionId);
}
