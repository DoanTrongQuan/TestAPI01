package com.example.testapi01.services;

import com.example.testapi01.models.Account;
import com.example.testapi01.models.Article;
import com.example.testapi01.models.Course;
import com.example.testapi01.models.Enrollment;
import com.example.testapi01.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServices implements IAccountServices{
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private PermissionsRepo permissionsRepo;
    @Autowired
    private EnrollmentRepo enrollmentRepo;
    @Autowired
    private ArticleRepo articleRepo;
    @Autowired
    private CourseServices courseServices;
    @Autowired
    private CourseRepo courseRepo;

    @Override
    public ResponseEntity<String> addNewAccount(Account account) {
        if(!accountRepo.existsById(account.getAccountID())) {
            if (permissionsRepo.existsById(account.getPermissions().getPermissionsID())) {
                if(checkPassword(account.getPassword()) && checkUserName(account)){
                    accountRepo.save(account);
                    return new ResponseEntity<>("Add Account success", HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>("Add faild", HttpStatus.NOT_FOUND);
    }

    @Override
    public boolean updateAccount(Account account) {
        if(accountRepo.existsById(account.getAccountID())){
            if(checkUserName(account)){
                accountRepo.save(account);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteAccount(int id) {
        Account account = accountRepo.findById(id).orElse(null);
        if(account != null){
            for (Enrollment enrollment:enrollmentRepo.findAll()) {
                 if(enrollment.getAccount().getAccountID()==id){
                     Course course = courseRepo.findById(enrollment.getCourse().getCourseID()).orElse(null);
                     enrollmentRepo.delete(enrollment);
                     courseServices.updateNumberStudents(course.getCourseID());
                 }
            }
            for (Article article:articleRepo.findAll() ) {
                if(article.getAccount().getAccountID()==id){
                    articleRepo.delete(article);
                }
            }
            accountRepo.delete(account);
            return true;
        }
        return false;
    }

    @Override
    public List<Account> displayAccount() {
        return null;
    }

    @Override
    public Page<Account> displayAccByPage(int numberPage, int limit) {
        Pageable pageable = PageRequest.of(numberPage-1, limit);
        return accountRepo.findAll(pageable);
    }

    public boolean checkPassword(String password){
        boolean checkLetter = false;
        boolean checkDigit = false;
        boolean checkspecialCharacter = false;
        for (char ch:password.toCharArray()) {
             if(Character.isDigit(ch)){
                 checkDigit = true;
             }else if(Character.isLetter(ch)){
                 checkLetter = true;
             }else if (!Character.isLetter(ch) && !Character.isDigit(ch)){
                 checkspecialCharacter = true;
             }
        }
        if(checkLetter && checkDigit && checkspecialCharacter){
            return true;
        }
        return false;
    }
    public boolean checkUserName(Account account){
        for (Account account1:accountRepo.findAll()) {
             if(account1.getAccountID() != account.getAccountID()){
                 if(account1.getAccountName().equals(account.getAccountName())){
                     return false;
                 }
             }
        }
        return true;
    }
}
