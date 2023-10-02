package com.example.testapi01.repository;

import com.example.testapi01.models.ArticleType;
import com.example.testapi01.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleTypeRepo extends JpaRepository<ArticleType, Integer> {
    Page<ArticleType> findAll(Pageable pageable);
}
