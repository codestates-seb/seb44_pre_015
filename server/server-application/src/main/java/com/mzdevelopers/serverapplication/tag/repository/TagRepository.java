package com.mzdevelopers.serverapplication.tag.repository;

import com.mzdevelopers.serverapplication.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
