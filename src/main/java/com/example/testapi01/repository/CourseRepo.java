package com.example.testapi01.repository;

import com.example.testapi01.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {
      Course findByCourseName(String courseName);
      Page<Course> findAll(Pageable pageable);
}
