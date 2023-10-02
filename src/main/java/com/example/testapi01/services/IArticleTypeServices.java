package com.example.testapi01.services;

import com.example.testapi01.models.ArticleType;
import org.springframework.data.domain.Page;

public interface IArticleTypeServices {
    public boolean addNewArticleType(ArticleType articleType);
    public boolean updateArticleType(ArticleType articleType);
    public boolean deleteArticleType(int id);
    public Page<ArticleType> displayArticleType(int pageNumber, int limit);
}
