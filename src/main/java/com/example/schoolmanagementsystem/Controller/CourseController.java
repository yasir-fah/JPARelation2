package com.example.schoolmanagementsystem.Controller;

import com.example.schoolmanagementsystem.Api.ApiResponse;
import com.example.schoolmanagementsystem.Model.Course;
import com.example.schoolmanagementsystem.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllCourses(){
        return ResponseEntity.status(200).body(courseService.getAllCourses());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCourse(@Valid @RequestBody Course course){
        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("course added"));
    }

    @PutMapping("/update/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable Integer courseId, @Valid @RequestBody Course course){
        courseService.update(courseId,course);
        return ResponseEntity.status(200).body(new ApiResponse("course updated"));
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Integer courseId){
        courseService.deleteCourse(courseId);
        return ResponseEntity.status(200).body(new ApiResponse("course deleted"));
    }

    @PutMapping("/update/assign/{cId}/to/{teId}")
    public ResponseEntity<?> assignCourseToTeacher(@PathVariable Integer teId, @PathVariable Integer cId){
        courseService.assignCourseToTeacher(teId,cId);
        return ResponseEntity.status(200).body(new ApiResponse("course assigned to teacher"));
    }

    @GetMapping("/get/teacher/{courseId}")
    public ResponseEntity<?> getTeacherByCourseId(@PathVariable Integer courseId){
        return ResponseEntity.status(200).body(courseService.getTeacherByCourseId(courseId));
    }

    @GetMapping("/get/students/{courseId}")
    public ResponseEntity<?> getAllStudentByCourse(@PathVariable Integer courseId) {
        return ResponseEntity.status(200).body(courseService.getAllStudentByCourse(courseId));
    }





}
