package com.example.testapi01.services;

import com.example.testapi01.models.Topic;
import org.springframework.data.domain.Page;

public interface ITopicServices {
    public boolean addNewTopic(Topic topic);
    public boolean updateTopic(Topic topic);
    public boolean deleteTopic(int id);
    public Page<Topic> displayTopicByPage(int numberPage, int limit);
}
