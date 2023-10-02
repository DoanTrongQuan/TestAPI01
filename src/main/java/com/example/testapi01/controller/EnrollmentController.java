package com.example.testapi01.controller;

import com.example.testapi01.models.Enrollment;
import com.example.testapi01.services.EnrollmentServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
public class EnrollmentController {
    @Autowired
    private EnrollmentServices enrollmentServices;
    @RequestMapping(value = "add/New/Enrollment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addNewEnrollment(@RequestBody String enrollment){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Enrollment enrollment1 = gson.fromJson(enrollment, Enrollment.class);
        return  enrollmentServices.enrollStudentInCourse(enrollment1);
    }
    @RequestMapping(value = "update/Enrollment", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateEnrollment(@RequestBody String enrollment){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Enrollment enrollment1 = gson.fromJson(enrollment, Enrollment.class);
        return  enrollmentServices.updateEnrollment(enrollment1);
    }
    @RequestMapping(value = "delete/Enrollment", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteEnrollment(@RequestParam int id ){
        boolean check = enrollmentServices.deleteEnrollment(id);
        if(check){
            return "Delele success";
        }
        return "Delete faild";
    }
    @RequestMapping(value = "display/Enrollment/by/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Enrollment> displayEnrollmentByPage(@RequestParam (defaultValue = "0") int pagenumber,
                                               @RequestParam (defaultValue = "2") int limit){
        return enrollmentServices.displayEnrollmentByPage(pagenumber,limit);
    }


}
