package com.project.EMS.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDepartmentRequest {

    @NotBlank(message = "name cannot be blank")
    private String departmentName;

    private Boolean isActive = true;


}
