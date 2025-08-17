package com.example.schoolmanagementsystem.Service;

import com.example.schoolmanagementsystem.Api.ApiException;
import com.example.schoolmanagementsystem.Model.Course;
import com.example.schoolmanagementsystem.Model.Student;
import com.example.schoolmanagementsystem.Repository.CourseRepository;
import com.example.schoolmanagementsystem.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }

    public void addStudent(Student student){
        studentRepository.save(student);
    }

    public void updateStudent(Integer id, Student student){

        Student oldStudent = studentRepository.findStudentById(id);
        if(oldStudent == null){
            throw new ApiException("student not founded");
        }

        oldStudent.setName(student.getName());
        oldStudent.setAge(student.getAge());
        oldStudent.setMajor(student.getMajor());
        studentRepository.save(oldStudent);
    }

    public void deleteStudent(Integer id){

        Student oldStudent = studentRepository.findStudentById(id);
        if(oldStudent == null){
            throw new ApiException("student not founded");
        }
        studentRepository.delete(oldStudent);
    }

    /// assign course to student and vise versa
    public void assignStudentToCourse(Integer studentId, Integer courseId){

        Student student = studentRepository.findStudentById(studentId);
        Course course = courseRepository.findCourseById(courseId);

        if(student == null || course == null){
            throw new ApiException("student or course not found");
        }

        student.getCourse().add(course);
        course.getStudent().add(student);

        studentRepository.save(student);
        courseRepository.save(course);
    }

    /// JPA relation 3 first endpoint:
    public void changeMajor(Integer studentId, String newMajor){

        // check if student exist:
        Student student = studentRepository.findStudentById(studentId);
        if(student == null || newMajor.isBlank()){
            throw new ApiException("student or new major not found");
        }

        List<Course> courses = courseRepository.findAll();
        for (Course course : courses){
            if(course.getStudent().contains(student)){
                course.getStudent().remove(student);
                student.getCourse().remove(course);
            }
        }
        student.setMajor(newMajor);

        studentRepository.save(student);
        courseRepository.saveAll(courses);
    }

}
