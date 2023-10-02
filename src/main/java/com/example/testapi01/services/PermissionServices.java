package com.example.testapi01.services;

import com.example.testapi01.models.*;
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
public class PermissionServices implements IPermissionServices {
    @Autowired
    private PermissionsRepo permissionsRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private EnrollmentRepo enrollmentRepo;
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private CourseServices courseServices;
    @Autowired
    private ArticleRepo articleRepo;


    @Override
    public ResponseEntity<String> addNewPermission(Permissions permissions) {
        if (!permissionsRepo.existsById(permissions.getPermissionsID())) {
            permissionsRepo.save(permissions);
            return new ResponseEntity<>("Add success", HttpStatus.OK);
        }
        return new ResponseEntity<>("Add faild", HttpStatus.NOT_FOUND);
    }

    @Override
    public boolean updatePermission(Permissions permissions) {
        if (permissionsRepo.existsById(permissions.getPermissionsID())) {
                permissionsRepo.save(permissions);
            return true;
        }
        return false;
    }

    @Override
    public boolean deletePermission(int id) {
        if (permissionsRepo.existsById(id)) {
            Permissions permissions = permissionsRepo.findById(id).orElse(null);
            for (Account account : accountRepo.findAll()) {
                if (account.getPermissions().getPermissionsID() == id) {
                    for (Enrollment enrollment : enrollmentRepo.findAll()) {
                        if (enrollment.getAccount().getAccountID() == account.getAccountID()) {
                            Course course = courseRepo.findById(enrollment.getCourse().getCourseID()).orElse(null);
                            enrollmentRepo.delete(enrollment);
                            courseServices.updateNumberStudents(course.getCourseID());
                        }
                    }
                    for (Article article : articleRepo.findAll()) {
                        if (article.getAccount().getAccountID() == account.getAccountID()) {
                            articleRepo.delete(article);
                        }
                    }
                    accountRepo.delete(account);
                    permissionsRepo.delete(permissions);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public List<Permissions> displayPermission() {
        List<Permissions> permissions = permissionsRepo.findAll();
        return permissions;
    }

    @Override
    public Page<Permissions> displayPermissionByPage(int numberPage, int limit) {
        Pageable pageable = PageRequest.of(numberPage-1, limit);
        return permissionsRepo.findAll(pageable);
    }
}
