package com.example.schoolmanagementsystem.Service;

import com.example.schoolmanagementsystem.Api.ApiException;
import com.example.schoolmanagementsystem.Model.Course;
import com.example.schoolmanagementsystem.Model.Student;
import com.example.schoolmanagementsystem.Model.Teacher;
import com.example.schoolmanagementsystem.OutDTO.CourseOutDTO;
import com.example.schoolmanagementsystem.OutDTO.StudentOutDTO;
import com.example.schoolmanagementsystem.Repository.CourseRepository;
import com.example.schoolmanagementsystem.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    /// add course separately without teacher.
    public void addCourse(Course course){
        courseRepository.save(course);
    }

    public void update(Integer courseId, Course course){

        // check if course exist
        Course oldCourse = courseRepository.findCourseById(courseId);
        if(oldCourse == null){
            throw new ApiException("course not founded");
        }

        oldCourse.setName(course.getName());
        courseRepository.save(oldCourse);
    }

    public void deleteCourse(Integer courseId){

        // check if course exist
        Course oldCourse = courseRepository.findCourseById(courseId);
        if(oldCourse == null){
            throw new ApiException("course not founded");
        }

        courseRepository.delete(oldCourse);
    }


    public void assignCourseToTeacher(Integer teacherId, Integer courseId){

        // check if teacher exist:
        Teacher teacher = teacherRepository.findTeacherById(teacherId);

        // check if course exist
        Course course = courseRepository.findCourseById(courseId);

        if(teacher == null || course == null){
            throw new ApiException("course or teacher not found");
        }

        course.setTeacher(teacher);
        courseRepository.save(course);
    }


    /// last endpoint from JPA relation 2 - return teacher name from course id:
    public CourseOutDTO getTeacherByCourseId(Integer courseId){

        // check if course exists:
        Course course = courseRepository.findCourseById(courseId);
        if(course == null ){
            throw new ApiException("course not found");
        }
        return new CourseOutDTO(course.getTeacher().getName());
    }

    /// last endpoint at JPA relation 3:
    public List<StudentOutDTO> getAllStudentByCourse(Integer CourseId){

        Course course = courseRepository.findCourseById(CourseId);
        if(course == null){
            throw new ApiException("student  not found");
        }

        List<StudentOutDTO> studentList = new ArrayList<>();

        for (Student c : course.getStudent()){
            studentList.add(new StudentOutDTO(c.getName(),c.getAge(),c.getMajor()));
        }
        return studentList;
    }

}
