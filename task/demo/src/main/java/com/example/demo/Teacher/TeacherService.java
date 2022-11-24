package com.example.demo.Teacher;

import com.example.demo.Student.Student;
import com.example.demo.Student.StudentRepository;
import com.example.demo.Student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        boolean exists = teacherRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("This teacher doesn't exist");
        }
        this.teacherRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, Teacher teacher) {
        Teacher oldTeacher = this.teacherRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Teacher with id "+ id + " doesn't exist."));
        teacher.validatePut();
        oldTeacher.setAge(teacher.getAge());
        oldTeacher.setEmail(teacher.getEmail());
        oldTeacher.setName(teacher.getName());
        if (teacher.getSurname() != null) {
            oldTeacher.setSurname(teacher.getSurname());
        }
        if (teacher.getSubject() != null) {
            oldTeacher.setSubject((teacher.getSubject()));
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
