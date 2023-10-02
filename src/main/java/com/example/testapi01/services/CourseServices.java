package com.example.testapi01.services;

import com.example.testapi01.models.Course;
import com.example.testapi01.models.CourseType;
import com.example.testapi01.models.Enrollment;
import com.example.testapi01.repository.CourseRepo;
import com.example.testapi01.repository.CourseTypeRepo;
import com.example.testapi01.repository.EnrollmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServices implements ICourseServices{
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private CourseTypeRepo courseTypeRepo;
    @Autowired
    private EnrollmentRepo enrollmentRepo;

    @Override
    public boolean addNewCourse(Course course) {
        if(!courseRepo.existsById(course.getCourseID())) {
            if (courseTypeRepo.existsById(course.getCoursetype().getCourseTypeID())) {
                courseRepo.save(course);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateCourse(Course course) {
        if(courseRepo.existsById(course.getCourseID())) {
            Course course1 = courseRepo.findById(course.getCourseID()).orElse(null);
            if (courseTypeRepo.existsById(course.getCoursetype().getCourseTypeID())) {
                course1.setCoursetype(course.getCoursetype());
                courseRepo.save(course);
                for (Enrollment x:enrollmentRepo.findAll() ) {
                    if(x.getCourse().getCourseID()==course.getCourseID()){
                        x.setCourse(course);
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteCourse(int id) {
        if(courseRepo.existsById(id)){
            Course course= courseRepo.findById(id).orElse(null);
            courseRepo.deleteById(id);
            for (Enrollment enrollment:enrollmentRepo.findAll()) {
                 if(enrollment.getCourse().getCourseID() == course.getCourseID()){
                     enrollmentRepo.delete(enrollment);
                 }
            }
            return true;
        }
        return false;
    }


    @Override
    public List<Course> displayCourse() {
        List<Course> courses = courseRepo.findAll();
        return courses;
    }

    @Override
    public Course displayCourseByName(String courseName) {
        Course course= courseRepo.findByCourseName(courseName);
        return course;
    }
    public Page<Course> getCourseByPage(int pageNumber, int limit){
        Pageable pageable = PageRequest.of(pageNumber-1, limit);
        return courseRepo.findAll(pageable);
    }
    public void updateNumberStudents(int id) {
        Course course = courseRepo.findById(id).orElse(null);
        int numberStudent = 0;
        for (Enrollment x : enrollmentRepo.findAll()) {
            if (x.getCourse().getCourseID() == id) {
                numberStudent++;
            }
        }
        course.setNumberOfStudent(numberStudent);
        courseRepo.save(course);
    }
}
