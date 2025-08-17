package com.example.schoolmanagementsystem.Repository;

import com.example.schoolmanagementsystem.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {

    Teacher findTeacherById(Integer id);

    @Query("select t from Teacher t where t.address.id=:addressId")
    Teacher giveMeTeacherByAddressId(Integer addressId);
}
