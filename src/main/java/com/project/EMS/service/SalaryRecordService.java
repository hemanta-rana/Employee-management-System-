package com.project.EMS.service;

import com.project.EMS.dto.ResponseDto.SalaryRecordResponse;
import com.project.EMS.dto.requestDto.CreateSalaryRecordRequest;

import java.util.List;

public interface SalaryRecordService {
    SalaryRecordResponse addSalaryRecord(CreateSalaryRecordRequest createSalaryRecordRequest);
    SalaryRecordResponse getCurrentSalary(Long SalaryRecordId);
   List <SalaryRecordResponse> getSalaryHistory(Long employeeId);

}
