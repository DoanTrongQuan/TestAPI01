package com.example.testapi01.services;

import com.example.testapi01.models.Enrollment;
import com.example.testapi01.models.StudyStatus;
import com.example.testapi01.repository.EnrollmentRepo;
import com.example.testapi01.repository.StudyStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyStatusServices implements IStudyStatusServices{
    @Autowired
    private StudyStatusRepo studyStatusRepo;
    @Autowired
    private EnrollmentRepo enrollmentRepo;
    @Override
    public boolean addNewStudyStatus(StudyStatus studyStatus) {
        if(!studyStatusRepo.existsById(studyStatus.getStudyStatusID())){
            studyStatusRepo.save(studyStatus);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStudyStatus(StudyStatus studyStatus) {
        if(studyStatusRepo.existsById(studyStatus.getStudyStatusID())){
            studyStatusRepo.save(studyStatus);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteStudyStatus(String statusName) {
        StudyStatus studyStatus = studyStatusRepo.findByStatusName(statusName);
        if(studyStatusRepo.existsById(studyStatus.getStudyStatusID())){
            studyStatusRepo.deleteById(studyStatus.getStudyStatusID());
            for (Enrollment enrollment:enrollmentRepo.findAll()) {
                 if(enrollment.getStudystatus().getStudyStatusID()==studyStatus.getStudyStatusID()){
                     enrollment.setStudystatus(null);
                     enrollmentRepo.save(enrollment);
                 }
            }
            return true;
        }
        return false;
    }

    @Override
    public List<StudyStatus> displayStudyStatus() {
        List<StudyStatus> studyStatuses = studyStatusRepo.findAll();
        return studyStatuses;
    }

    @Override
    public Page<StudyStatus> displayStudyStatusByPage(int numberPage, int limit) {
        Pageable pageable = PageRequest.of(numberPage-1, limit);
        return studyStatusRepo.findAll(pageable);
    }
}
