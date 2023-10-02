package com.example.testapi01.services;

import com.example.testapi01.models.*;
import com.example.testapi01.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnrollmentServices implements IEnrollmentServices {
    @Autowired
    private EnrollmentRepo enrollmentRepo;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private StudyStatusRepo studyStatusRepo;
    @Autowired
    private CourseServices courseServices;
    @Autowired
    private AccountRepo accountRepo;

    @Override
    public ResponseEntity<String> enrollStudentInCourse(Enrollment enrollment) {
        Course course = courseRepo.findById(enrollment.getCourse().getCourseID()).orElse(null);
        if (course != null) {
            if (studentRepo.existsById(enrollment.getStudents().getStudentID())) {
                StudyStatus studyStatus = studyStatusRepo.findByStatusName(enrollment.getStudystatus().getStatusName());
                if (studyStatus != null) {
                    enrollment.setStudystatus(studyStatus);
                    enrollment.setCourse(course);
                    if (checkRegistrationDate(enrollment)) {
                        updateDate(enrollment);
                        enrollmentRepo.save(enrollment);
                        if(enrollment.getStudystatus().getStatusName().equals("dang hoc") ){
                            courseServices.updateNumberStudents(course.getCourseID());
                        }else if (enrollment.getStudystatus().getStatusName().equals("hoc xong")||enrollment.getStudystatus().getStatusName().equals("chua hoc xong")){
                            enrollmentRepo.delete(enrollment);
                            courseServices.updateNumberStudents(course.getCourseID());
                        }
                    } else {
                        return new ResponseEntity<>("Start Date Invalid", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity<>("Tinh trang hoc " + enrollment.getStudystatus().getStatusName() + " khong ton tai", HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>("Khong ton tai  hoc sinh co id " + enrollment.getStudents().getStudentID(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Khoa hoc " + enrollment.getCourse().getCourseName() + " khong ton tai", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Dang ki thanh cong", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateEnrollment(Enrollment enrollment) {
        Students students = studentRepo.findById(enrollment.getStudents().getStudentID()).orElse(null);
        StudyStatus studyStatus = studyStatusRepo.findById(enrollment.getStudystatus().getStudyStatusID()).orElse(null);
        Account account = accountRepo.findById(enrollment.getAccount().getAccountID()).orElse(null);
        Enrollment enrollmentOld = enrollmentRepo.findById(enrollment.getEnrollment_id()).orElse(null);

        if(enrollmentOld != null && students != null && studyStatus != null && account != null && courseRepo.existsById(enrollment.getCourse().getCourseID())){
            Course course = courseRepo.findById(enrollment.getCourse().getCourseID()).orElse(null);
            if(enrollment.getStudystatus().getStatusName().equals("dang hoc")) {
                if (enrollmentOld.getRegistrationDate().compareTo(enrollment.getStartDate()) < 0) {
                    LocalDate endDate = enrollment.getStartDate().plusDays(enrollment.getCourse().getStudyTime());
                    enrollment.setEndDate(endDate);
                    enrollment.setRegistrationDate(enrollmentOld.getRegistrationDate());
                    int idCourseOld = enrollmentOld.getCourse().getCourseID();
                    enrollmentRepo.save(enrollment);
                    courseServices.updateNumberStudents(idCourseOld);
                    courseServices.updateNumberStudents(enrollment.getCourse().getCourseID());
                    return new ResponseEntity<>("Update success", HttpStatus.OK);
                }else {
                    return new ResponseEntity<>("Invalid start date or end date", HttpStatus.NOT_FOUND);
                }
            }
            else if(enrollment.getStudystatus().getStatusName().equals("hoc xong") || enrollment.getStudystatus().getStatusName().equals("chua hoan thanh")){
                enrollmentRepo.delete(enrollmentOld);
                courseServices.updateNumberStudents(course.getCourseID());
                return new ResponseEntity<>("Update success", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Account or student or Course or Enrollment not exist", HttpStatus.NOT_FOUND);
    }

    @Override
    public boolean deleteEnrollment(int id) {
        Enrollment enrollment = enrollmentRepo.findById(id).orElse(null);
        if(enrollmentRepo.existsById(id)){
            enrollmentRepo.delete(enrollment);
            return true;
        }
        return false;
    }

    @Override
    public List<Enrollment> displayEnrollment() {
        List<Enrollment> enrollments = enrollmentRepo.findAll();
        return enrollments;
    }

    @Override
    public Page<Enrollment> displayEnrollmentByPage(int numberPage, int limit) {
        Pageable pageable = PageRequest.of(numberPage-1, limit);
        return enrollmentRepo.findAll(pageable);
    }


    public void updateDate(Enrollment enrollment) {
        Course course = courseRepo.findById(enrollment.getCourse().getCourseID()).orElse(null);
        LocalDate endDate = enrollment.getStartDate().plusDays(course.getStudyTime());
        enrollment.setEndDate(endDate);
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = localDateTime.toLocalDate();
        enrollment.setRegistrationDate(localDate);

    }

    public boolean checkRegistrationDate(Enrollment enrollment) {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = localDateTime.toLocalDate();
        int number = localDate.compareTo(enrollment.getStartDate());
        if (number < 0) {
            return true;
        }
        return false;
    }

}
