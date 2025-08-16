package com.example.schoolmanagementsystem.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {

    private Integer teacher_id;


    @NotEmpty(message = "area should not be empty")
    @Size(min = 3, max = 30, message = "area length 3-30")
    private String area;


    @NotEmpty(message = "street should not be empty")
    @Size(min = 3, max = 30, message = "street length 3-30")
    private String street;


    @NotNull(message = "building number can't be empty")
    private Integer buildNumber;

}
