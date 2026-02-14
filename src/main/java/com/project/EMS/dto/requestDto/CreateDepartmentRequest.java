package com.project.EMS.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDepartmentRequest {

    @NotBlank(message = "name cannot be blank")
    private String departmentName;

    private Boolean isActive  ;

}
