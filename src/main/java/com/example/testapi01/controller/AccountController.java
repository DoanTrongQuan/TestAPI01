package com.example.testapi01.controller;

import com.example.testapi01.models.Account;
import com.example.testapi01.services.AccountServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
public class AccountController {
    @Autowired
    private AccountServices accountServices;
    @RequestMapping(value = "add/New/Account", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addNewAccount(@RequestBody String account){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Account account1= gson.fromJson(account,Account.class);
        return accountServices.addNewAccount(account1);
    }
    @RequestMapping(value = "update/Account", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateAccount(@RequestBody String account){
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        Account account1= gson.fromJson(account,Account.class);
        boolean check = accountServices.updateAccount(account1);
        if(check){
            return "Update success";
        }
       return "Update faild";
    }
    @RequestMapping(value = "delete/Account", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteAccount(@RequestParam int id){
        boolean check = accountServices.deleteAccount(id);
        if(check){
            return "delete success";
        }
        return "delete faild";
    }
    @RequestMapping(value = "display/Account/by/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Account> displayAccountByPage(@RequestParam (defaultValue = "0") int pagenumber,
                                              @RequestParam (defaultValue = "2") int limit){
        return accountServices.displayAccByPage(pagenumber,limit);
    }

}
