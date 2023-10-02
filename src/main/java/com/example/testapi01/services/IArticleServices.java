package com.example.testapi01.services;

import com.example.testapi01.models.Article;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IArticleServices {
    public boolean addNewArticle(Article article);
    public boolean updateArticle(Article article);
    public boolean deleteArticle(int id);
    public List<Article> searchArticleByName(String name);
    public Page<Article> displayArticleByPage(int numberPage, int limit);
}
