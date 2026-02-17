package com.project.EMS.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSalaryRecordRequest {
    @NotNull(message = "basic salary cannot be blank ")
    @Positive(message = "Basic salary must be greater then 0 ")
    private BigDecimal basicSalary;
    private BigDecimal bonus;
    private BigDecimal deduction ;
    @NotNull(message = "Effective date cannot be null ")
    private LocalDate effectiveDate;

    private Long employeeId;
}
