package com.example.testapi01.controller;

import com.example.testapi01.models.Course;
import com.example.testapi01.services.CourseServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseServices courseServices;
    @RequestMapping(value = "add/new/course", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addNewCourse(@RequestBody String course){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Course course1 = gson.fromJson(course, Course.class);
        boolean check = courseServices.addNewCourse(course1);
        if(check){
            return "them thanh cong";
        }
        return "them that bai";
    }
    @RequestMapping(value = "update/course", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateCourse(@RequestBody String course){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Course course1 = gson.fromJson(course, Course.class);
        boolean check = courseServices.updateCourse(course1);
        if(check){
            return "sua thanh cong";
        }
        return "sua that bai";
    }
    @RequestMapping(value = "delete/course", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteCourse(@RequestParam int id) {
        boolean check = courseServices.deleteCourse(id);
        if(check){
            return "xoa thanh cong";
        }
        return "xoa that bai";
    }
    @RequestMapping(value = "display/course", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> displayCourse() {
        return courseServices.displayCourse();
    }
    @RequestMapping(value = "display/course/byName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Course displayCourseByName(@RequestParam String nameCourse) {
        return courseServices.displayCourseByName(nameCourse);
    }
    @RequestMapping(value = "get/course/byPage", method =RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Course> getCourseByPage(@RequestParam (defaultValue = "0") int pagenumber,
                                        @RequestParam (defaultValue = "2") int limit){
        return courseServices.getCourseByPage(pagenumber,limit);
    }

}
