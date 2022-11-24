package com.example.demo.Teacher;


import com.example.demo.Serializer.CustomStudentSerializer;
import com.example.demo.Student.Student;
import com.example.demo.Validator.Validator;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table
public class Teacher {
    @Id
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String subject;
    @ManyToMany
    @JoinTable(
            name = "teacher_student_pair",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    @JsonSerialize(using = CustomStudentSerializer.class)
    private List<Student> students;

    public Teacher(){}

    public Teacher (String name, String surname, int age, String email, String subject) {
        Validator validator = new Validator();
        if (!validator.emailIsValid(email)) {
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void assignStudent(Student student) {
        if (this.students != null) {
            if (!this.students.contains(student)) {
                this.students.add(student);
            }
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
        if (!validator.ageIsValid(this.getAge())) {
            throw new IllegalArgumentException("'age' must be at least 19");
        }
    }

}