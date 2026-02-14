package com.project.EMS.dto.requestDto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequest {

    @NotBlank(message = "name cannot be blank ")
    private String name;


    @Email(message = "Invalid email format")
    @NotBlank(message = "email cannot be blank")
    private String email;


    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Invalid Phone Number")
    private String phone;


    @NotNull(message = "Department Id is required ")
    private Long departmentId ;

    private Boolean isActive;





}
