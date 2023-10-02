package com.example.testapi01.controller;

import com.example.testapi01.models.CourseType;
import com.example.testapi01.services.CourseTypeServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
public class CourseTypeController {
    @Autowired
    private CourseTypeServices courseTypeServices;
    @RequestMapping(value = "add/new/courseType", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addNewCourseType(@RequestBody String courseType){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        CourseType courseType1= gson.fromJson(courseType, CourseType.class);
        boolean check = courseTypeServices.addNewCourseType(courseType1);
        if(check){
            return "them thanh cong";
        }
        return "them that bai";
    }
    @RequestMapping(value = "update/courseType", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateCourseType(@RequestBody String courseType){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        CourseType courseType1= gson.fromJson(courseType, CourseType.class);
        boolean check = courseTypeServices.updateCourseType(courseType1);
        if(check){
            return "sua thanh cong";
        }
        return "sua that bai";
    }
    @RequestMapping(value = "delete/courseType", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteCourseType(@RequestParam int id) {
        boolean check = courseTypeServices.deleteCourseType(id);
        if (check) {
            return "xoa thanh cong";
        }
        return "xoa that bai";
    }
    @RequestMapping(value = "display/CourseType/by/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<CourseType> displayTopicByPage(@RequestParam (defaultValue = "0") int pagenumber,
                                               @RequestParam (defaultValue = "2") int limit){
        return courseTypeServices.displayCourseTypeByPage(pagenumber,limit);
    }

}
