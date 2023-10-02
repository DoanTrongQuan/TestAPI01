package com.example.testapi01.controller;

import com.example.testapi01.models.ArticleType;
import com.example.testapi01.services.ArticleTypeServices;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

@RestController
public class ArticleTypeController {
    @Autowired
    private ArticleTypeServices articleTypeServices;

    @RequestMapping(value = "delete/ArticleType", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteArticleType(@RequestParam int id) {
        boolean check = articleTypeServices.deleteArticleType(id);
        if (check) {
            return "delete success";
        }
        return "delete faild";
    }

    @RequestMapping(value = "add/New/ArticleType", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addNewArticleType(@RequestBody String articleType) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        ArticleType articleType1 = gson.fromJson(articleType, ArticleType.class);
        boolean check = articleTypeServices.addNewArticleType(articleType1);
        if (check) {
            return "Add success";
        }
        return "Add faild";
    }

    @RequestMapping(value = "update/ArticleType", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updatePermission(@RequestBody String articleType) {
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
            }
        }).create();
        ArticleType articleType1 = gson.fromJson(articleType, ArticleType.class);
        boolean check = articleTypeServices.updateArticleType(articleType1);
        if (check) {
            return "Update success";
        }
        return "Update faild";
    }
    @RequestMapping(value = "get/aricleType/byPage", method =RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<ArticleType> displayArticleTypeByPage(@RequestParam (defaultValue = "0") int pagenumber,
                                                  @RequestParam (defaultValue = "2") int limit){
        return articleTypeServices.displayArticleType(pagenumber,limit);
    }
}
