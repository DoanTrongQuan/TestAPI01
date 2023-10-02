package com.example.testapi01.repository;

import com.example.testapi01.models.Course;
import com.example.testapi01.models.CourseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTypeRepo extends JpaRepository<CourseType,Integer> {
    Page<CourseType> findAll(Pageable pageable);

}
