package com.example.testapi01.services;

import com.example.testapi01.models.Students;
import com.example.testapi01.models.Topic;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IStudentServices {
    public ResponseEntity<String> addNewStudent(Students students);
    public ResponseEntity<String> updateStudent(Students students);
    public boolean deleteStudent(int id);
    public List<Students> displayStudent();
    public List<Students> searchStudentByName(String fullName);
    public Students searchStudentByEmail(String email);
    public Page<Students> displayStudentByPage(int numberPage, int limit);
}
