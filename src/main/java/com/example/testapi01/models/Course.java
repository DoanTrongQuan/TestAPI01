package com.example.testapi01.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseID;
    @Column(name = "courseName")
    private String courseName;
    @Column(name = "studyTime")
    private int studyTime;

    @Column(name = "introduction")
    private String introduction;
    @Column(name = "conTent")
    private String conTent;
    @Column(name = "Fee")
    private double fee;
    @Column(name = "numberOfStudent")
    private int numberOfStudent;
    @Column(name = "numberOfLessons")
    private int numberOfLessons;
    @Column(name = "images")
    private byte[] image;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "courseTypeID", foreignKey = @ForeignKey(name = "fk_Course_courseType"))
    private CourseType coursetype;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    @JsonBackReference
    private Set<Enrollment> enrollmentSet;

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(int studyTime) {
        this.studyTime = studyTime;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getConTent() {
        return conTent;
    }

    public void setConTent(String conTent) {
        this.conTent = conTent;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(int numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    public int getNumberOfLessons() {
        return numberOfLessons;
    }

    public void setNumberOfLessons(int numberOfLessons) {
        this.numberOfLessons = numberOfLessons;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public CourseType getCoursetype() {
        return coursetype;
    }

    public void setCoursetype(CourseType coursetype) {
        this.coursetype = coursetype;
    }

    public Set<Enrollment> getEnrollmentSet() {
        return enrollmentSet;
    }

    public void setEnrollmentSet(Set<Enrollment> enrollmentSet) {
        this.enrollmentSet = enrollmentSet;
    }
}
