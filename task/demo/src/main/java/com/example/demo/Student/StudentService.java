package com.example.demo.Student;

import com.example.demo.Teacher.Teacher;
import com.example.demo.Teacher.TeacherRepository;
import com.example.demo.Teacher.TeacherService;
import com.example.demo.Validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;



    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> findAll() {
        return this.studentRepository.findAll();
    }

    public List<Student> findAllByName() {
        return this.studentRepository.findAllByOrderByNameAsc();
    }

    public void save(Student student) {
        this.studentRepository.save(student);
    }
    public void assignTeacher(Long studentID, TeacherService teacherService, Long teacherID) {
        Teacher teacher = teacherService.findById(teacherID);
        Student student = this.studentRepository.findById(studentID)
                .orElseThrow(() -> new IllegalStateException("Student with id + inputId +  doesn't exist."));
        student.assignTeacher(teacher);
        teacher.assignStudent(student);
        this.save(student);
        teacherService.save(teacher);
    }

    public void removeTeacher(Long studentID, TeacherService teacherService, Long teacherID) {
        Student student = this.studentRepository.findById(studentID)
                .orElseThrow(() -> new IllegalStateException("Student with id "+ studentID + " doesn't exist."));
        student.removeTeacher(teacherID);
        Teacher teacher = teacherService.findById(teacherID);
        teacher.removeStudent(studentID);
        this.save(student);
        teacherService.save(teacher);
    }
    public void add(Student student) {
        this.studentRepository.save(student);
    }

    public void delete(Long id) {
        boolean exists = studentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("This student doesn't exist");
        }
        this.studentRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, Student student) {
        Student oldStudent = this.studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Student with id "+ id + " doesn't exist."));
        student.validatePut();
        oldStudent.setAge(student.getAge());
        oldStudent.setEmail(student.getEmail());
        oldStudent.setName(student.getName());
        if (student.getSurname() != null) {
            oldStudent.setSurname(student.getSurname());
        }
        if (student.getMajor() != null) {
            oldStudent.setMajor((student.getMajor()));
        }

    }

    public List<Student> sortByName(ArrayList<Student> students) {
        return this.studentRepository.findAllByOrderByNameAsc();
    }

    public ArrayList<Student> filterByTeacher(List<Student> students, Teacher teacher) {
        return students.stream().
                filter(x -> x.getTeachers().contains(teacher))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Student> filterByName(List<Student> students, String name) {
        return students.stream().
                filter(x -> x.getName().equals(name))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Student> filterBySurname(List<Student> students, String surname) {
        return students.stream().
                filter(x -> x.getSurname().equals(surname))
                .collect(Collectors.toCollection(ArrayList::new));
    }


    public Student findById(Long id) {
        List<Student> students = this.studentRepository.findAll();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == (id)) {
                return students.get(i);
            }
        }
        return null;
    }

}
