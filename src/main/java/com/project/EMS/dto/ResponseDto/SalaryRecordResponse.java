package com.project.EMS.dto.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryRecordResponse {

    private Long id;
    private Long employeeId;
    private BigDecimal basicSalary;
    private BigDecimal bonus;
    private BigDecimal deduction;
    private LocalDateTime createdAt;
    private LocalDate effectiveDate;
}
