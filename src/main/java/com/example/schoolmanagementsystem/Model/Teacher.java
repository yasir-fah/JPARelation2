package com.example.schoolmanagementsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name can't be empty")
    @Size(min = 4, max = 25, message = "name length should be between 4-25")
    @Column(nullable = false, length = 25)
    private String name;


    @NotNull(message = "age can't be empty")
    @Min(value = 21, message = "minimum age should be 21")
    @Column(nullable = false)
    private Integer age;


    @NotEmpty(message = "email can't be empty")
    @Email(message = "email should be valid")
    @Column(nullable = false)
    private String email;


    @NotEmpty(message = "password can't be empty")
    @Size(min = 8, message = "password should be 8 of length at least")
    @Column(nullable = false)
    private String password;


    @NotNull(message = "salary can't be empty")
    @Positive(message = "salary should be positive")
    @Column(nullable = false)
    private double salary;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "teacher")
    @PrimaryKeyJoinColumn
    private Address address;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private List<Course> course;

}
