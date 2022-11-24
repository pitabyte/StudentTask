package com.example.demo.Student;

import com.example.demo.Serializer.CustomStudentSerializer;
import com.example.demo.Serializer.CustomTeacherSerializer;
import com.example.demo.Teacher.Teacher;
import com.example.demo.Validator.Validator;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table
public class Student {
    @Id
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String major;

    @ManyToMany(mappedBy = "students")
    @JsonSerialize(using = CustomTeacherSerializer.class)
    private List<Teacher> teachers;

    public Student() {
    }

    public Student(String name, String surname, int age, String email, String major) {
        Validator validator = new Validator();
        if (!validator.emailIsValid(email)) {
            throw new IllegalArgumentException(email + " is of an incorrect format");
        }
        if (!validator.nameIsValid(name)) {
            throw new IllegalArgumentException("'name' must be at least 3 characters long");
        }
        if (!validator.ageIsValid(age)) {
            throw new IllegalArgumentException("'age' must be at least 19");
        }
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.major = major;
        this.teachers = new ArrayList<>();
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void assignTeacher(Teacher teacher) {
        if (this.teachers != null) {
            if (!this.teachers.contains(teacher)) {
                this.teachers.add(teacher);
            }
        }
    }

    public void removeTeacher(Long id) {
        Iterator<Teacher> iterator = this.teachers.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
            }
        }
    }


    public void validate() {
        Validator validator = new Validator();
        if (!validator.emailIsValid(this.getEmail())) {
            throw new IllegalArgumentException("email:  " + email + " is of an incorrect format");
        }
        if (!validator.nameIsValid(this.getName())) {
            throw new IllegalArgumentException("'name' must be at least 3 characters long");
        }
        if (!validator.ageIsValid(this.getAge())) {
            throw new IllegalArgumentException("'age' must be at least 19");
        }
    }

    public void validatePut() {
        Validator validator = new Validator();
        if (this.getEmail() != null) {
            if (!validator.emailIsValid(this.getEmail())) {
                throw new IllegalArgumentException("email:  " + email + " is of an incorrect format");
            }
        }
        if (this.getName() != null) {
            if (!validator.nameIsValid(this.getName())) {
                throw new IllegalArgumentException("'name' must be at least 3 characters long");
            }
        }
        if (this.getAge() != 0) {
            if (!validator.ageIsValid(this.getAge())) {
                throw new IllegalArgumentException("'age' must be at least 19");
            }
        }
    }
}