package com.example.testapi01.services;

import com.example.testapi01.models.Account;
import com.example.testapi01.models.Topic;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAccountServices {
    public ResponseEntity<String> addNewAccount(Account account);
    public boolean updateAccount(Account account);
    public boolean deleteAccount(int id);
    public List<Account> displayAccount();
    public Page<Account> displayAccByPage(int numberPage, int limit);
}
