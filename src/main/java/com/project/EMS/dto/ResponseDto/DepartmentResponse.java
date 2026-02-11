package com.project.EMS.dto.ResponseDto;

import com.project.EMS.entity.Employee;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentResponse {
    private Long id ;

    @NotBlank(message = "name cannot be blank")
    private String departmentName;

    private Boolean isActive = true;

    @CreationTimestamp
    private LocalDateTime createdAt ;

    private List<Employee> employees;
}
