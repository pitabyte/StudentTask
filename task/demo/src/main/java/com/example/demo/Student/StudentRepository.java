package com.example.demo.Student;

import com.example.demo.Student.Student;
import com.example.demo.Student.StudentRepository;
import com.example.demo.Teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public List<Student> findAllByOrderByNameAsc();

}