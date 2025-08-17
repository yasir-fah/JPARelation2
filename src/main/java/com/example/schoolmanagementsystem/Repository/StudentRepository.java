package com.example.schoolmanagementsystem.Repository;

import com.example.schoolmanagementsystem.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findStudentById(Integer id);
}
