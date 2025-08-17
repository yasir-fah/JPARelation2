package com.example.schoolmanagementsystem.Controller;

import com.example.schoolmanagementsystem.Api.ApiResponse;
import com.example.schoolmanagementsystem.Model.Student;
import com.example.schoolmanagementsystem.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllStudents() {
        return ResponseEntity.status(200).body(studentService.getAllStudent());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student student) {
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("student added"));
    }

    @PutMapping("/update/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable Integer studentId,
                                           @Valid @RequestBody Student student) {
        studentService.updateStudent(studentId, student);
        return ResponseEntity.status(200).body(new ApiResponse("student updated"));
    }

    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable Integer studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.status(200).body(new ApiResponse("student deleted"));
    }

    // assign course to student (and vice versa)
    @PutMapping("/assign/{studentId}/to/{courseId}")
    public ResponseEntity<?> assignStudentToCourse(@PathVariable Integer studentId,
                                                   @PathVariable Integer courseId) {
        studentService.assignStudentToCourse(studentId, courseId);
        return ResponseEntity.status(200).body(new ApiResponse("student assigned to course"));
    }

    @PutMapping("/update/major/{studentId}/to/{newMajor}")
    public ResponseEntity<?> changeMajor(@PathVariable Integer studentId, @PathVariable String newMajor) {
        studentService.changeMajor(studentId, newMajor);
        return ResponseEntity.status(200).body(new ApiResponse("student major changed"));
    }
}
