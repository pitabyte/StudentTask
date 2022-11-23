package com.example.demo.Student;

import com.example.demo.Teacher.Teacher;
import com.example.demo.Teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentRepository getStudentRepository() {
        return this.studentRepository;
    }

    public void assignTeacher(Long studentID, TeacherRepository teacherRepository, Long teacherID) {
        Teacher teacher = teacherRepository.findById(teacherID);
        Student student = this.studentRepository.findById(studentID);
        student.assignTeacher(teacher);
        teacher.assignStudent(student);
    }

    public void removeTeacher(Long studentID, TeacherRepository teacherRepository, Long teacherID) {
        this.studentRepository.findById(studentID).removeTeacher(teacherID);
        teacherRepository.findById(teacherID).removeStudent(studentID);
    }
    public void add(Student student) {
        this.studentRepository.getAll().add(student);
    }

    public void delete(Long id) {
        this.studentRepository.getAll().removeIf(x -> x.getId() == id);
    }

    public void update(Long id, Student student) {
        student.setId(id);
        ArrayList<Student> studentList = this.studentRepository.getAll();
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId() == id) {
                studentList.set(i, student);
                break;
            }
        }
    }
}
