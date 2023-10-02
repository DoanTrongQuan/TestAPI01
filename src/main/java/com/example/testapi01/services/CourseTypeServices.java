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

@Service
public class CourseTypeServices implements ICourseTypeServices {
    @Autowired
    private CourseTypeRepo courseTypeRepo;
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private EnrollmentRepo enrollmentRepo;

    @Override
    public boolean addNewCourseType(CourseType courseType) {
        if (!courseTypeRepo.existsById(courseType.getCourseTypeID())) {
            courseTypeRepo.save(courseType);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCourseType(CourseType courseType) {
        if (courseTypeRepo.existsById(courseType.getCourseTypeID())) {
            courseTypeRepo.save(courseType);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCourseType(int id) {
        if (courseTypeRepo.existsById(id)) {
            CourseType courseType = courseTypeRepo.findById(id).orElse(null);
            for (Course course : courseRepo.findAll()) {
                if (course.getCoursetype().getCourseTypeID() == id) {
                    for (Enrollment enrollment : enrollmentRepo.findAll()) {
                        if (enrollment.getCourse().getCourseID() == course.getCourseID()) {
                            enrollmentRepo.delete(enrollment);
                        }
                    }
                    courseRepo.delete(course);
                }
            }
            courseTypeRepo.delete(courseType);
            return true;
        }
        return false;
    }

    @Override
    public Page<CourseType> displayCourseTypeByPage(int numberPage, int limit) {
        Pageable pageable = PageRequest.of(numberPage-1, limit);
        return courseTypeRepo.findAll(pageable);
    }
}
