package com.example.demo.Person;

import com.example.demo.Validator.Validator;

import java.util.Objects;


public abstract class Person {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String email;

    public Person(Long id, String name, String surname, int age, String email) {
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
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
    }

    public Long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}