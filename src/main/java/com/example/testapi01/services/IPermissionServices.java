package com.example.testapi01.services;

import com.example.testapi01.models.Permissions;
import com.example.testapi01.models.Topic;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPermissionServices {
    public ResponseEntity<String> addNewPermission(Permissions permissions);
    public boolean updatePermission(Permissions permissions);
    public boolean deletePermission(int id);
    public List<Permissions> displayPermission();
    public Page<Permissions> displayPermissionByPage(int numberPage, int limit);

}
