package com.example.demo.Teacher;

import com.example.demo.Person.Person;
import com.example.demo.Person.PersonRepository;
import com.example.demo.Student.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TeacherRepository {
    private ArrayList<Teacher> teachers = new ArrayList<>();

    public ArrayList<Teacher> getAll() {
        return this.teachers;
    }

    public ArrayList<Teacher> getAllByName() {
        return this.teachers.stream().
                sorted((x1, x2) -> { return x1.getName().compareTo(x2.getName());
                }).collect(Collectors.toCollection(ArrayList::new));
    }

    public Teacher findById(Long id) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId() == (id)) {
                return teachers.get(i);
            }
        }
        return null;
    }

    public ArrayList<Teacher> filterByStudent(ArrayList<Teacher> teachers, Student student) {
        return teachers.stream().
                filter(x -> x.getStudents().contains(student))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Teacher> filterByName(ArrayList<Teacher> teachers, String name) {
        return teachers.stream().
                filter(x -> x.getName().contains(name))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Teacher> filterBySurname(ArrayList<Teacher> teachers, String surname) {
        return teachers.stream().
                filter(x -> x.getSurname().contains(surname))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Teacher> search(String name) {
        return teachers.stream().filter(x -> x.getName().equals(name)).collect(Collectors.toList());
    }
}
