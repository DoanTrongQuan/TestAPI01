package com.example.testapi01.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enrollment_id;
    @Column(name = "registrationDate")
    private LocalDate registrationDate;
    @Column(name = "startDate")
    private LocalDate startDate;
    @Column(name ="endDate")
    private LocalDate endDate;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name ="courseID", foreignKey = @ForeignKey(name = ("fk_Enrollment_course")))
    private Course course;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name ="studyStatusID", foreignKey = @ForeignKey ( name = ("fk_Enrollment_studystatus")))
    private StudyStatus studystatus;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name="studentID", foreignKey = @ForeignKey(name ="fk_Enrollment_students"))
    private Students students;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name="accountID",foreignKey = @ForeignKey(name ="fk_Enrollment_Account"))
    private Account account;

    public int getEnrollment_id() {
        return enrollment_id;
    }

    public void setEnrollment_id(int enrollment_id) {
        this.enrollment_id = enrollment_id;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public StudyStatus getStudystatus() {
        return studystatus;
    }

    public void setStudystatus(StudyStatus studystatus) {
        this.studystatus = studystatus;
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
