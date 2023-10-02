package com.example.testapi01.repository;

import com.example.testapi01.models.Course;
import com.example.testapi01.models.StudyStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyStatusRepo extends JpaRepository<StudyStatus, Integer> {
    StudyStatus findByStatusName(String statusName);
    Page<StudyStatus> findAll(Pageable pageable);

}
