package com.example.testapi01.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.util.Set;

@Entity
@Table(name="account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountID;
    @Column(name="UserName")
    private String userName;
    @Column(name = "AccountName")
    private String accountName;
    @Column(name ="Password")
    private String password;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name ="permissionsID", foreignKey = @ForeignKey(name="fk_Account_Permissions"))
    private Permissions permissions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    @JsonBackReference
    private Set<Article> articleSet;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "account")
    @JsonBackReference
    private Set<Enrollment> enrollmentSet;

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public Set<Article> getArticleSet() {
        return articleSet;
    }

    public void setArticleSet(Set<Article> articleSet) {
        this.articleSet = articleSet;
    }

    public Set<Enrollment> getEnrollmentSet() {
        return enrollmentSet;
    }

    public void setEnrollmentSet(Set<Enrollment> enrollmentSet) {
        this.enrollmentSet = enrollmentSet;
    }
}
