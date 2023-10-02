package com.example.testapi01.services;

import com.example.testapi01.models.Article;
import com.example.testapi01.models.ArticleType;
import com.example.testapi01.models.Topic;
import com.example.testapi01.repository.ArticleRepo;
import com.example.testapi01.repository.ArticleTypeRepo;
import com.example.testapi01.repository.TopicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleTypeServices implements IArticleTypeServices{
    @Autowired
    private ArticleTypeRepo articleTypeRepo;
    @Autowired
    private TopicRepo topicRepo;
    @Autowired
    private ArticleRepo articleRepo;

    @Override
    public boolean addNewArticleType(ArticleType articleType) {
        if(!articleTypeRepo.existsById(articleType.getArticleTypeID())){
            articleTypeRepo.save(articleType);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateArticleType(ArticleType articleType) {
        if(articleTypeRepo.existsById(articleType.getArticleTypeID())){
            articleTypeRepo.save(articleType);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteArticleType(int id) {
        if(articleTypeRepo.existsById(id)){
            ArticleType articleType = articleTypeRepo.findById(id).orElse(null);
            for (Topic topic: topicRepo.findAll()) {
                 if(topic.getArticletype().getArticleTypeID()==id){
                     for (Article article:articleRepo.findAll() ) {
                         if(article.getTopic().getTopicID()==topic.getTopicID()){
                             articleRepo.delete(article);
                         }
                     }
                     topicRepo.delete(topic);
                 }
                articleTypeRepo.delete(articleType);
                 return true;
            }
        }
        return false;
    }

    @Override
    public Page<ArticleType> displayArticleType(int pageNumber, int limit) {
        Pageable pageable = PageRequest.of(pageNumber-1, limit);
        return articleTypeRepo.findAll(pageable);
    }


}
