package com.example.testapi01.repository;

import com.example.testapi01.models.Article;
import com.example.testapi01.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Integer> {
    List<Article> findByArticleName(String articleName);
    Page<Article> findAll(Pageable pageable);

}
