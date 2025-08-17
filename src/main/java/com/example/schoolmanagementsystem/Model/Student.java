package com.example.schoolmanagementsystem.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name can't be empty")
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull(message = "name can't be empty")
    @Min(value = 17, message = "age should be 17 and above")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "name can't be empty")
    @Column(nullable = false)
    private String major;


    @ManyToMany(mappedBy = "student")
    @JsonIgnore
    private Set<Course> course;

}
