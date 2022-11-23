package com.example.demo.StudentTeacherController;

import com.example.demo.Student.Student;
import com.example.demo.Student.StudentService;
import com.example.demo.Teacher.Teacher;
import com.example.demo.Teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@RestController
    @RequestMapping("/api")
    public class StudentTeacherController {

        @Autowired
        private StudentService studentService;
        @Autowired
        private TeacherService teacherService;
        private final AtomicLong studentCounter = new AtomicLong();
        private final AtomicLong teacherCounter = new AtomicLong();

        @PostMapping(path = "student")
        public void addStudent(@RequestBody Student student) {
            student.setId(studentCounter.incrementAndGet());
            studentService.add(student);
        }

        @GetMapping(path = "student")
        public List<Student> getAllStudents(@RequestParam(required = false) String sort,
                                            @RequestParam(required = false) String teacherID,
                                            @RequestParam(required = false) String name,
                                            @RequestParam(required = false) String surname) {
            ArrayList<Student> output = studentService.getStudentRepository().getAll();
            if (sort != null && sort.equals("true")) {
                output = studentService.getStudentRepository().getAllByName();
            }
            if (teacherID != null) {
                Long longTeacherID = Long.valueOf(teacherID);
                Teacher teacher = teacherService.getTeacherRepository().findById(longTeacherID);
                output = studentService.getStudentRepository().filterByTeacher(output, teacher);
            }
            if (name != null) {
                output = studentService.getStudentRepository().filterByName(output, name);
            }
            if (surname != null) {
                output = studentService.getStudentRepository().filterBySurname(output, surname);
            }
            return output;
        }


        @DeleteMapping(path = "student/{id}")
        public void deleteStudent(@PathVariable Long id) {
            studentService.delete(id);
        }

        @PutMapping(path = "student/{id}")
        public void updateStudent(@RequestBody Student student, @PathVariable Long id) {
            studentService.update(id, student);
        }

        @PostMapping(path = "student/{studentID}/assign/{teacherID}")
        public void assignTeacher(@PathVariable Long studentID, @PathVariable Long teacherID) {
            studentService.assignTeacher(studentID, this.teacherService.getTeacherRepository(), teacherID);
        }

        @PostMapping(path = "student/{studentID}/remove/{teacherID}")
        public void removeTeacher(@PathVariable Long studentID, @PathVariable Long teacherID) {
            studentService.removeTeacher(studentID,this.teacherService.getTeacherRepository(), teacherID);
        }


        @PostMapping(path = "teacher")
        public void addTeacher(@RequestBody Teacher teacher) {
            teacher.setId(teacherCounter.incrementAndGet());
            teacherService.add(teacher);
        }

        @GetMapping(path = "teacher")
        public List<Teacher> getTeachers(@RequestParam(required = false) String sort,
                                            @RequestParam(required = false) String studentID,
                                            @RequestParam(required = false) String name,
                                            @RequestParam(required = false) String surname) {
            ArrayList<Teacher> output = teacherService.getTeacherRepository().getAll();
            if (sort != null && sort.equals("true")) {
                output = teacherService.getTeacherRepository().getAllByName();
            }
            if (studentID != null) {
                Long longStudentID = Long.valueOf(studentID);
                Student student = studentService.getStudentRepository().findById(longStudentID);
                output = teacherService.getTeacherRepository().filterByStudent(output, student);
            }
            if (name != null) {
                output = teacherService.getTeacherRepository().filterByName(output, name);
            }
            if (surname != null) {
                output = teacherService.getTeacherRepository().filterBySurname(output, surname);
            }
            return output;
        }


        @DeleteMapping(path = "teacher/{id}")
        public void deleteTeacher(@PathVariable Long id) {
            teacherService.delete(id);
        }

        @PutMapping(path = "teacher/{id}")
        public void updateTeacher(@RequestBody Teacher teacher, @PathVariable Long id) {
            teacherService.update(id, teacher);
        }

        @PostMapping(path = "teacher/{teacherID}/assign/{studentID}")
        public void assignStudent(@PathVariable Long teacherID, @PathVariable Long studentID) {
            teacherService.assignStudent(teacherID, this.studentService.getStudentRepository(), studentID);
        }

        @PostMapping(path = "teacher/{teacherID}/remove/{studentID}")
        public void removeStudent(@PathVariable Long teacherID, @PathVariable Long studentID) {
            teacherService.removeStudent(teacherID, this.studentService.getStudentRepository(), studentID);
        }
    }

