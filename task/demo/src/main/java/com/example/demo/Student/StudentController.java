package com.example.demo.Student;

import com.example.demo.Teacher.Teacher;
import com.example.demo.Teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    private final AtomicLong studentCounter = new AtomicLong();

    @PostMapping(path = "")
    public void addStudent(@RequestBody Student student) {
        student.setId(studentCounter.incrementAndGet());
        student.validate();
        this.studentService.add(student);
    }

    @GetMapping(path = "")
    public List<Student> getAllStudents(@RequestParam(required = false) String sort,
                                        @RequestParam(required = false) String teacherID,
                                        @RequestParam(required = false) String name,
                                        @RequestParam(required = false) String surname,
                                        @RequestParam(required = false) String size,
                                        @RequestParam(required = false) String page) {

        List<Student> output = studentService.findAll();
        if (sort != null && sort.equals("true")) {
            output = studentService.findAllByName();
        }
        if (teacherID != null) {
            Long longTeacherID = Long.valueOf(teacherID);
            Teacher teacher = teacherService.findById(longTeacherID);
            output = studentService.filterByTeacher(output, teacher);
        }
        if (name != null) {
            output = studentService.filterByName(output, name);
        }
        if (surname != null) {
            output = studentService.filterBySurname(output, surname);
        }
        if (page != null && size != null) {
            PagedListHolder pagedListHolder = new PagedListHolder(output);
            pagedListHolder.setPageSize(Integer.valueOf(size));
            pagedListHolder.setPage(Integer.valueOf(page));
            return pagedListHolder.getPageList();
        }
        return output;
    }


    @DeleteMapping(path = "{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void updateStudent(@RequestBody Student student, @PathVariable Long id) {
        studentService.update(id, student);
    }

    @PostMapping(path = "{studentID}/assign/{teacherID}")
    public void assignTeacher(@PathVariable Long studentID, @PathVariable Long teacherID) {
        studentService.assignTeacher(studentID, this.teacherService, teacherID);
    }

    @PostMapping(path = "{studentID}/remove/{teacherID}")
    public void removeTeacher(@PathVariable Long studentID, @PathVariable Long teacherID) {
        studentService.removeTeacher(studentID, this.teacherService, teacherID);
    }
}

