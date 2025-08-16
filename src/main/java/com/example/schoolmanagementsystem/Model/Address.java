package com.example.schoolmanagementsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Address {

    @Id
    private Integer id;

    @NotEmpty(message = "area should not be empty")
    @Size(min = 3, max = 30, message = "area length 3-30")
    @Column(columnDefinition = "varchar(30) not null")
    private String area;

    @NotEmpty(message = "street should not be empty")
    @Size(min = 3, max = 30, message = "street length 3-30")
    @Column(columnDefinition = "varchar(30) not null")
    private String street;

    @NotNull(message = "building number can't be empty")
    @Column(columnDefinition = "int not null")
    private Integer buildNumber;

    @OneToOne
    @MapsId
    @JsonIgnore // ignore the object to prevent the loop
    private Teacher teacher;
}
