package com.example.testapi01.services;

import com.example.testapi01.models.Course;

import java.util.List;

public interface ICourseServices {
    public boolean addNewCourse(Course course);
    public boolean updateCourse(Course course);
    public boolean deleteCourse(int id);
    public List<Course> displayCourse();
    public Course displayCourseByName(String nameCourse);
}
