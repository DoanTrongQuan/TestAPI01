package com.example.testapi01.controller;

import com.example.testapi01.models.StudyStatus;
import com.example.testapi01.services.StudyStatusServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

@RestController
public class StudyStatusController {
    @Autowired
    private StudyStatusServices studyStatusServices;
    @RequestMapping(value = "add/new/StudyStatus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addNewStudyStatus(@RequestBody String studyStatus){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        StudyStatus studyStatus1= gson.fromJson(studyStatus, StudyStatus.class);
        boolean check = studyStatusServices.addNewStudyStatus(studyStatus1);
        if(check){
            return "them thanh cong";
        }
        return "da ton tai tinh trang hoc";
    }
    @RequestMapping(value = "update/StudyStatus", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateStudyStatus(@RequestBody String studyStatus){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        StudyStatus studyStatus1= gson.fromJson(studyStatus, StudyStatus.class);
        boolean check = studyStatusServices.updateStudyStatus(studyStatus1);
        if(check){
            return "sua  thanh cong";
        }
        return "sua that bai";
    }
    @RequestMapping(value = "delete/StudyStatus", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteStudyStatus(@RequestParam String studyStatusName){
        boolean check = studyStatusServices.deleteStudyStatus(studyStatusName);
        if(check){
            return "them thanh cong";
        }
        return "da ton tai tinh trang hoc";
    }
    public List<StudyStatus> displayStudyStatus(){
        return studyStatusServices.displayStudyStatus();
    }
    @RequestMapping(value = "display/StudyStatus/by/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<StudyStatus> displayStudyStatusByPage(@RequestParam (defaultValue = "0") int pagenumber,
                                                @RequestParam (defaultValue = "2") int limit){
        return studyStatusServices.displayStudyStatusByPage(pagenumber, limit);
    }


}
