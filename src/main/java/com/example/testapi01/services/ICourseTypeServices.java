package com.example.testapi01.services;

import com.example.testapi01.models.CourseType;
import com.example.testapi01.models.Topic;
import org.springframework.data.domain.Page;

public interface ICourseTypeServices {
    public boolean addNewCourseType(CourseType courseType);
    public boolean updateCourseType(CourseType courseType);
    public boolean deleteCourseType(int id);
    public Page<CourseType> displayCourseTypeByPage(int numberPage, int limit);

}
