package com.example.schoolmanagementsystem.Service;

import com.example.schoolmanagementsystem.Api.ApiException;
import com.example.schoolmanagementsystem.Model.Teacher;
import com.example.schoolmanagementsystem.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.event.ListDataEvent;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }

    public void addTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }

    public void updateTeacher(Integer id, Teacher teacher){
        Teacher oldTeacher = teacherRepository.findTeacherById(id);

        if(oldTeacher == null){
            throw new ApiException("teacher id not found");
        }

        oldTeacher.setName(teacher.getName());
        oldTeacher.setAge(teacher.getId());
        oldTeacher.setEmail(teacher.getEmail());
        oldTeacher.setPassword(teacher.getPassword());
        oldTeacher.setSalary(teacher.getSalary());

        teacherRepository.save(oldTeacher);
    }

    public void deleteTeacher(Integer id){
        Teacher oldTeacher = teacherRepository.findTeacherById(id);

        if(oldTeacher == null){
            throw new ApiException("teacher id not found");
        }

        teacherRepository.delete(oldTeacher);
    }


    /// get all teacher information by his id:
    public Teacher getTeacherInfo(Integer teacherId){
        Teacher teacher = teacherRepository.findTeacherById(teacherId);

        if(teacher == null){
            throw new ApiException("teacher id not found");
        }
        return teacher;
    }

}
