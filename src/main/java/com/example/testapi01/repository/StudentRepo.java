package com.example.testapi01.repository;

import com.example.testapi01.models.Course;
import com.example.testapi01.models.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Students, Integer> {
    List<Students> findByFullName(String fullName);
    Students findByEmail(String email);
    Students findByNumberPhone(String numberPhone);
    Page<Students> findAll(Pageable pageable);

}
