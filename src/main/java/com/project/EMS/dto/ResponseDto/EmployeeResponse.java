package com.project.EMS.dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

    private Long id;
    private String name;
    private String email;

    private String phone;

    private Boolean isActive;

    private LocalDateTime createdAt;

    private Long departmentId;




}
