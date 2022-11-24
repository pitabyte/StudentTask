package com.example.demo.Teacher;

import com.example.demo.Student.Student;
import com.example.demo.Student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
@RestController
    @RequestMapping("/api/teacher")
    public class TeacherController {

        @Autowired
        private StudentService studentService;
        @Autowired
        private TeacherService teacherService;
        private final AtomicLong teacherCounter = new AtomicLong();

        @PostMapping(path = "")
        public void addTeacher(@RequestBody Teacher teacher) {
            teacher.validate();
            teacher.setId(teacherCounter.incrementAndGet());
            teacherService.add(teacher);
        }

        @GetMapping(path = "")
        public List<Teacher> getTeachers(@RequestParam(required = false) String sort,
                                         @RequestParam(required = false) String studentID,
                                         @RequestParam(required = false) String name,
                                         @RequestParam(required = false) String surname,
                                         @RequestParam(required = false) String size,
                                         @RequestParam(required = false) String page)
        {
            List<Teacher> output = teacherService.findAll();
            if (sort != null && sort.equals("true")) {
                output = teacherService.findAllByName();
            }
            if (studentID != null) {
                Long longStudentID = Long.valueOf(studentID);
                Student student = studentService.findById(longStudentID);
                output = teacherService.filterByStudent(output, student);
            }
            if (name != null) {
                output = teacherService.filterByName(output, name);
            }
            if (surname != null) {
                output = teacherService.filterBySurname(output, surname);
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
        public void deleteTeacher(@PathVariable Long id) {
            teacherService.delete(id);
        }

        @PutMapping(path = "{id}")
        public void updateTeacher(@RequestBody Teacher teacher, @PathVariable Long id) {
            teacherService.update(id, teacher);
        }

        @PostMapping(path = "{teacherID}/assign/{studentID}")
        public void assignStudent(@PathVariable Long teacherID, @PathVariable Long studentID) {
            teacherService.assignStudent(teacherID, this.studentService, studentID);
        }

        @PostMapping(path = "{teacherID}/remove/{studentID}")
        public void removeStudent(@PathVariable Long teacherID, @PathVariable Long studentID) {
            teacherService.removeStudent(teacherID, this.studentService, studentID);
        }
}

