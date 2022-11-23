package com.example.demo.Teacher;

import com.example.demo.Person.Person;
import com.example.demo.Serializer.CustomStudentSerializer;
import com.example.demo.Student.Student;
import com.example.demo.Validator.Validator;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.Iterator;


public class Teacher {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String subject;
    @JsonSerialize(using = CustomStudentSerializer.class)
    private ArrayList<Student> students;

    public Teacher (String name, String surname, int age, String email, String subject) {
        Validator validator = new Validator();
        if (!validator.patternMatches(email)) {
            throw new IllegalArgumentException(email + " is of an incorrect format");
        }
        if (name.length() < 3) {
            throw new IllegalArgumentException("'name' must be at least 3 characters long");
        }
        if (age < 19) {
            throw new IllegalArgumentException("'age' must be at least 19");
        }
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.subject = subject;
        this.students = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void assignStudent(Student student) {
        if (!this.students.contains(student)) {
            this.students.add(student);
        }
    }

    public void removeStudent(Long id) {
        Iterator<Student> iterator = this.students.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
            }
        }
    }

}