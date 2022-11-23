package com.example.demo.Person;

import com.example.demo.Student.Student;
import com.example.demo.Teacher.Teacher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonRepository {
    private ArrayList<Person> people = new ArrayList<>();

    public ArrayList<Person> getAll() {
        return this.people;
    }

    public void add(Person person) {
        this.people.add(person);
    }

    public void delete(Long id) {
        this.people.removeIf(x -> x.getId() == id);
    }

    public Person findById(Long id) {
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getId() == (id)) {
                return people.get(i);
            }
        }
        return null;
    }

    public List<Person> search(String name) {
        return people.stream().filter(x -> x.getName().equals(name)).collect(Collectors.toList());
    }




}