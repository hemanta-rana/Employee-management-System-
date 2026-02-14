package com.project.EMS.dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse {
    private Long id ;

    private String departmentName;

    private Boolean isActive ;

    private LocalDateTime createdAt ;

    private Long employeeCount;

}
