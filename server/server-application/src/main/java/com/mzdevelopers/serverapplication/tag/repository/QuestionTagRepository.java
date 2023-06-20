package com.mzdevelopers.serverapplication.tag.repository;

import com.mzdevelopers.serverapplication.tag.entity.QuestionTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionTagRepository extends JpaRepository<QuestionTag, Long> {

    List<QuestionTag> findByQuestionQuestionId(Long questionId);
}
