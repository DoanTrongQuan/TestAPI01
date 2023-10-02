package com.example.testapi01.controller;

import com.example.testapi01.models.Permissions;
import com.example.testapi01.services.PermissionServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
public class PermissionController {
    @Autowired
    private PermissionServices permissionServices;
    @RequestMapping(value = "delete/Permission", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deletePermission(@RequestParam int id){
        boolean check = permissionServices.deletePermission(id);
        if(check){
            return "delete success";
        }
        return "delete faild";
    }
    @RequestMapping(value = "add/New//Permission", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addNewPermission(@RequestBody String permission){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Permissions permissions = gson.fromJson(permission, Permissions.class);
       return permissionServices.addNewPermission(permissions);
    }
    @RequestMapping(value = "update/Permission", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updatePermission(@RequestBody String permission){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Permissions permissions = gson.fromJson(permission, Permissions.class);
        boolean check = permissionServices.updatePermission(permissions);
        if(check){
            return "Update success";
        }
        return "Update faild";
    }
    @RequestMapping(value = "display/Permission/by/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Permissions> displayTopicByPage(@RequestParam (defaultValue = "0") int pagenumber,
                                                @RequestParam (defaultValue = "2") int limit){
        return permissionServices.displayPermissionByPage(pagenumber, limit);
    }
}
