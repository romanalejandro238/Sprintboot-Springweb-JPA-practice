package com.example.demo.student;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table
public class Student {

    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "student_sequence")

    private Long id;
    private String name;
    @Transient
    private Integer age;
    private LocalDate dayOfBirth;
    private String email;

    public Student() {

    }

    public Student(Long id,
            String name,
            LocalDate dayOfBirth,
            String email) {
        this.id = id;
        this.name = name;
        this.dayOfBirth = dayOfBirth;
        this.email = email;
    }

    public Student(
            String name,
            LocalDate dayOfBirth,
            String email) {
        this.name = name;
        this.dayOfBirth = dayOfBirth;
        this.email = email;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return Period.between(this.dayOfBirth, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {

    }

    public LocalDate getDayOfBirth() {
        return this.dayOfBirth;
    }

    public void setDayOfBirth(LocalDate dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dayOfBirth=" + dayOfBirth +
                ", email='" + email + '\'' +
                '}';
    }
}
