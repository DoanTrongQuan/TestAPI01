package com.example.testapi01.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.data.repository.cdi.Eager;

import java.util.Set;

@Entity
@Table(name ="StudyStatus")
public class StudyStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studyStatusID;

    @Column(name="statusName")
    private String statusName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studystatus")
    @JsonBackReference
    private Set<Enrollment> enrollmentSet;

    public int getStudyStatusID() {
        return studyStatusID;
    }

    public void setStudyStatusID(int studyStatusID) {
        this.studyStatusID = studyStatusID;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Set<Enrollment> getEnrollmentSet() {
        return enrollmentSet;
    }

    public void setEnrollmentSet(Set<Enrollment> enrollmentSet) {
        this.enrollmentSet = enrollmentSet;
    }
}
