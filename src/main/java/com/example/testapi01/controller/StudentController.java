package com.example.testapi01.controller;

import com.example.testapi01.models.Students;
import com.example.testapi01.services.StudentServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentServices studentServices;
    @RequestMapping(value = "add/New/Student", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addNewStudent(@RequestBody String student){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Students students= gson.fromJson(student, Students.class);
        return studentServices.addNewStudent(students);
    }
    @RequestMapping(value = "update/Student", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateStudent(@RequestBody String student){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Students students= gson.fromJson(student, Students.class);
        return studentServices.updateStudent(students);
    }
    @RequestMapping(value = "delete/Student", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteStudent(@RequestParam int id) {
        boolean check = studentServices.deleteStudent(id);
        if(check){
            return "Delete success";
        }
        return "Delete faild";
    }
    @RequestMapping(value = "display/Student", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Students> displayStudent() {
        return studentServices.displayStudent();
    }

    @RequestMapping(value = "search/student/ByName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Students> searchStudentByName(@RequestParam String fullName) {
        return studentServices.searchStudentByName(fullName);
    }
    @RequestMapping(value = "search/By/Email", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Students searchStudentByEmail(String email) {
        return studentServices.searchStudentByEmail(email);
    }
    @RequestMapping(value = "display/student/by/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Students> displayStudentByPage(@RequestParam (defaultValue = "0") int pagenumber,
                                               @RequestParam (defaultValue = "6") int limit){
        return studentServices.displayStudentByPage(pagenumber, limit);
    }



}

