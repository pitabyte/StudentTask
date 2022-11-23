package com.example.demo.Teacher;

import com.example.demo.Student.Student;
import com.example.demo.Student.StudentRepository;
import com.example.demo.Student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    @Autowired
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> findAll() {
        return this.teacherRepository.findAll();
    }

    public List<Teacher> findAllByName(){
        return this.teacherRepository.findAllByOrderByNameAsc();
    }

    public void assignStudent(Long teacherID, StudentService studentService, Long studentID) {
        Student student = studentService.findById(studentID);
        Teacher teacher = this.teacherRepository.findById(teacherID)
                .orElseThrow(() -> new IllegalStateException("Teacher with id + inputId +  doesn't exist."));
        teacher.assignStudent(student);
        student.assignTeacher(teacher);
        this.save(teacher);
        studentService.save(student);
    }

    public void removeStudent(Long teacherID, StudentService studentService, Long studentID) {
        Teacher teacher = this.teacherRepository.findById(teacherID)
                .orElseThrow(() -> new IllegalStateException("Teacher with id + inputId +  doesn't exist."));
        teacher.removeStudent(studentID);
        Student student = studentService.findById(studentID);
        student.removeTeacher(teacherID);
        this.save(teacher);
        studentService.save(student);
    }

    public void save(Teacher teacher) {
        this.teacherRepository.save(teacher);
    }

    public void add(Teacher person) {
        this.teacherRepository.save(person);
    }

    public void delete(Long id) {
        this.teacherRepository.findAll().removeIf(x -> x.getId() == id);
    }

    public void update(Long id, Teacher teacher) {
        teacher.setId(id);
        List<Teacher> teacherList = this.teacherRepository.findAll();
        for (int i = 0; i < teacherList.size(); i++) {
            if (teacherList.get(i).getId() == id) {
                teacherList.set(i, teacher);
                break;
            }
        }
    }

    public Teacher findById(Long id) {
        List<Teacher> teachers = this.teacherRepository.findAll();
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getId() == (id)) {
                return teachers.get(i);
            }
        }
        return null;
    }

    public ArrayList<Teacher> filterByStudent(List<Teacher> teachers, Student student) {
        return teachers.stream().
                filter(x -> x.getStudents().contains(student))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Teacher> filterByName(List<Teacher> teachers, String name) {
        return teachers.stream().
                filter(x -> x.getName().contains(name))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Teacher> filterBySurname(List<Teacher> teachers, String surname) {
        return teachers.stream().
                filter(x -> x.getSurname().contains(surname))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
