package com.example.testapi01.services;

import com.example.testapi01.models.StudyStatus;
import com.example.testapi01.models.Topic;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IStudyStatusServices {
    public boolean addNewStudyStatus(StudyStatus studyStatus);
    public boolean updateStudyStatus(StudyStatus studyStatus);
    public boolean deleteStudyStatus(String statusName);
    public List<StudyStatus> displayStudyStatus();
    public Page<StudyStatus> displayStudyStatusByPage(int numberPage, int limit);

}
