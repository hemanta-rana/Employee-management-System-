package com.project.EMS.service;

import com.project.EMS.dto.ResponseDto.SalaryRecordResponse;
import com.project.EMS.dto.requestDto.CreateSalaryRecordRequest;

public interface SalaryRecordService {
    SalaryRecordResponse addSalaryRecord(CreateSalaryRecordRequest createSalaryRecordRequest);
    SalaryRecordResponse getCurrentSalary(Long employeeId);
    SalaryRecordResponse getSalaryHistory(Long employeeId);

}
