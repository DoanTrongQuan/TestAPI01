package com.example.testapi01.services;

import com.example.testapi01.models.Article;
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
public class TopicServices implements ITopicServices{
    @Autowired
    private TopicRepo topicRepo;
    @Autowired
    private ArticleTypeRepo articleTypeRepo;
    @Autowired
    private ArticleRepo articleRepo;

    @Override
    public boolean addNewTopic(Topic topic) {
        if(!topicRepo.existsById(topic.getTopicID())) {
            if (articleTypeRepo.existsById(topic.getArticletype().getArticleTypeID())) {
                topicRepo.save(topic);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateTopic(Topic topic) {
        if(topicRepo.existsById(topic.getTopicID())){
            if(articleTypeRepo.existsById(topic.getArticletype().getArticleTypeID())) {
                topicRepo.save(topic);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteTopic(int id) {
        Topic topic = topicRepo.findById(id).orElse(null);
        if(topicRepo.existsById(id)) {
            for (Article article : articleRepo.findAll()) {
                if (article.getTopic().getTopicID() == id) {
                    articleRepo.delete(article);
                }
            }
            topicRepo.delete(topic);
            return true;
        }
        return false;
    }

    @Override
    public Page<Topic> displayTopicByPage(int numberPage, int limit) {
        Pageable pageable = PageRequest.of(numberPage-1, limit);
        return topicRepo.findAll(pageable);
    }
}
