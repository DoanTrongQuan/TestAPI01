package com.example.testapi01.services;

import com.example.testapi01.models.Account;
import com.example.testapi01.models.Article;
import com.example.testapi01.models.Topic;
import com.example.testapi01.repository.AccountRepo;
import com.example.testapi01.repository.ArticleRepo;
import com.example.testapi01.repository.TopicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServices implements IArticleServices{
    @Autowired
    private ArticleRepo articleRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private TopicRepo topicRepo;

    @Override
    public boolean addNewArticle(Article article) {
        Optional<Topic> topicOptional = topicRepo.findById(article.getTopic().getTopicID());
        Optional<Account> accountOptional = accountRepo.findById(article.getAccount().getAccountID());
        if(!articleRepo.existsById(article.getArticleID())){
            if(topicOptional.isPresent() && accountOptional.isPresent()){
                LocalDateTime localDateTime = LocalDateTime.now();
                LocalDate creatTime = localDateTime.toLocalDate();
                article.setCreateTime(creatTime);
                articleRepo.save(article);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateArticle(Article article) {
        Optional<Topic> topicOptional = topicRepo.findById(article.getTopic().getTopicID());
        Optional<Account> accountOptional = accountRepo.findById(article.getAccount().getAccountID());
        if(articleRepo.existsById(article.getArticleID())){
            if(topicOptional.isPresent() && accountOptional.isPresent()){
                LocalDateTime localDateTime = LocalDateTime.now();
                LocalDate creatTime = localDateTime.toLocalDate();
                article.setCreateTime(creatTime);
                articleRepo.save(article);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteArticle(int id) {
        Article article = articleRepo.findById(id).orElse(null);
        if(articleRepo.existsById(id)){
            articleRepo.delete(article);
            return true;
        }
        return false;
    }

    @Override
    public List<Article> searchArticleByName(String name) {
        List<Article> articles = articleRepo.findByArticleName(name);
        return articles;
    }

    @Override
    public Page<Article> displayArticleByPage(int numberPage, int limit) {
        Pageable pageable = PageRequest.of(numberPage-1, limit);
        return articleRepo.findAll(pageable);
    }
}
