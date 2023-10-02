package com.example.testapi01.services;

import com.example.testapi01.models.Enrollment;
import com.example.testapi01.models.Topic;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEnrollmentServices {
    public ResponseEntity<String> enrollStudentInCourse(Enrollment enrollment);
    public ResponseEntity<String> updateEnrollment(Enrollment enrollment);
    public  boolean deleteEnrollment(int id);
    public List<Enrollment> displayEnrollment();
    public Page<Enrollment> displayEnrollmentByPage(int numberPage, int limit);

}
