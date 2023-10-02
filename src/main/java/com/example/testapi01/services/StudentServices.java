package com.example.testapi01.services;

import com.example.testapi01.models.Course;
import com.example.testapi01.models.Enrollment;
import com.example.testapi01.models.Students;
import com.example.testapi01.repository.EnrollmentRepo;
import com.example.testapi01.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class StudentServices implements IStudentServices {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private EnrollmentRepo enrollmentRepo;
    @Autowired
    private CourseServices courseServices;

    @Override
    public ResponseEntity<String> addNewStudent(Students students) {
        Students students1 = students;
        if (!studentRepo.existsById(students1.getStudentID())) {
            if (checkEmail(students1)) {
                if (checkNumberPhone(students1)) {
                    students1.setFullName(formatName(students1.getFullName()));
                    studentRepo.save(students1);
                    return new ResponseEntity<>("Add succsess", HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>("Email or phone number already exists.", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> updateStudent(Students students) {
        Students students1 = studentRepo.findById(students.getStudentID()).orElse(null);
        if (students1 != null) {
            Students st = students1;
            if (checkEmail(students) && checkNumberPhone(students)) {
                studentRepo.save(students);
                return new ResponseEntity<>("Update succsess.", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Email or phone number already exists.", HttpStatus.NOT_FOUND);
    }


    @Override
    public boolean deleteStudent(int id) {
        Students students = studentRepo.findById(id).orElse(null);
        if (studentRepo.existsById(id)) {
            for (Enrollment enrollment : enrollmentRepo.findAll()) {
                if (enrollment.getStudents().getStudentID() == id) {
                    enrollmentRepo.delete(enrollment);
                    courseServices.updateNumberStudents(enrollment.getCourse().getCourseID());
                }
            }
            studentRepo.delete(students);
            return true;
        }
        return false;
    }

    @Override
    public List<Students> displayStudent() {
        List<Students> students = studentRepo.findAll();
        return students;
    }

    @Override
    public List<Students> searchStudentByName(String fullName) {
        List<Students> students = studentRepo.findByFullName(fullName);
        return students;
    }

    @Override
    public Students searchStudentByEmail(String email) {
        Students student = studentRepo.findByEmail(email);
        return student;
    }

    @Override
    public Page<Students> displayStudentByPage(int numberPage, int limit) {
        Pageable pageable = PageRequest.of(numberPage-1, limit);
        return studentRepo.findAll(pageable);
    }

    public String formatName(String name){
        String str = name.trim().toLowerCase();
        while (str.contains("  ")){
            str = str.replace("  ", " ");
        }
        String hoTen [] = str.split(" ");
        String ketQua = hoTen[0];
        ketQua = ketQua.substring(0,1).toUpperCase() + ketQua.substring(1).toLowerCase() + " ";
        for (int i = 1 ; i < hoTen.length; i++){
            ketQua += hoTen[i].substring(0,1).toUpperCase() + hoTen[i].substring(1).toLowerCase() + " ";
        }
        return ketQua;
    }

    public boolean checkEmail(Students students) {
        for (Students x : studentRepo.findAll()) {
            if (x.getStudentID() != students.getStudentID()) {
                if (x.getEmail().equals(students.getEmail())) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkNumberPhone(Students students) {
        for (Students x : studentRepo.findAll()) {
            if (x.getStudentID() != students.getStudentID()) {
                if (x.getNumberPhone().equals(students.getNumberPhone())) {
                    return false;
                }
            }
        }
        return true;
    }
}
