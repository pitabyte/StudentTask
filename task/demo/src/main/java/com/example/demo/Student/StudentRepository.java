package com.example.demo.Student;

import com.example.demo.Person.PersonRepository;
import com.example.demo.Student.Student;
import com.example.demo.Student.StudentRepository;
import com.example.demo.Teacher.Teacher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentRepository{
    private ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getAll() {
        return this.students;
    }

    public ArrayList<Student> getAllByName() {
        return this.students.stream().
                sorted((x1, x2) -> { return x1.getName().compareTo(x2.getName());
                }).collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Student> filterByTeacher(ArrayList<Student> students, Teacher teacher) {
        return students.stream().
                filter(x -> x.getTeachers().contains(teacher))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Student> filterByName(ArrayList<Student> students, String name) {
        return students.stream().
                filter(x -> x.getName().contains(name))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Student> filterBySurname(ArrayList<Student> students, String surname) {
        return students.stream().
                filter(x -> x.getSurname().contains(surname))
                .collect(Collectors.toCollection(ArrayList::new));
    }


    public Student findById(Long id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == (id)) {
                return students.get(i);
            }
        }
        return null;
    }

    public List<Student> search(String name) {
        return students.stream().filter(x -> x.getName().equals(name)).collect(Collectors.toList());
    }
}
