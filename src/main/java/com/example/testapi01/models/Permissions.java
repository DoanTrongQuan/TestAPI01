package com.example.testapi01.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name ="Permissions")
public class Permissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int permissionsID;
    @Column(name="PermissionName")
    private  String permissionName;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "permissions")
    @JsonBackReference
    private Set<Account> accountSet;

    public int getPermissionsID() {
        return permissionsID;
    }

    public void setPermissionsID(int permissionsID) {
        this.permissionsID = permissionsID;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Set<Account> getAccountSet() {
        return accountSet;
    }

    public void setAccountSet(Set<Account> accountSet) {
        this.accountSet = accountSet;
    }
}
