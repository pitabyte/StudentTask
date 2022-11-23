package com.example.demo.Teacher;

import com.example.demo.Student.Student;
import com.example.demo.Student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public TeacherRepository getTeacherRepository() {
        return this.teacherRepository;
    }


    public void assignStudent(Long teacherID, StudentRepository studentRepository, Long studentID) {
        Student student = studentRepository.findById(studentID);
        Teacher teacher = this.teacherRepository.findById(teacherID);
        teacher.assignStudent(student);
        student.assignTeacher(teacher);
    }

    public void removeStudent(Long teacherID, StudentRepository studentRepository, Long studentID) {
        this.teacherRepository.findById(teacherID).removeStudent(studentID);
        studentRepository.findById(studentID).removeTeacher(teacherID);
    }

    public void add(Teacher person) {
        this.teacherRepository.getAll().add(person);
    }

    public void delete(Long id) {
        this.teacherRepository.getAll().removeIf(x -> x.getId() == id);
    }

    public void update(Long id, Teacher teacher) {
        teacher.setId(id);
        ArrayList<Teacher> teacherList = this.teacherRepository.getAll();
        for (int i = 0; i < teacherList.size(); i++) {
            if (teacherList.get(i).getId() == id) {
                teacherList.set(i, teacher);
                break;
            }
        }
    }
}
