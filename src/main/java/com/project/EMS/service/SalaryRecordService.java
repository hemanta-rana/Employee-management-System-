package com.project.EMS.service;

import com.project.EMS.dto.ResponseDto.SalaryHistoryPageResponse;
import com.project.EMS.dto.ResponseDto.SalaryRecordResponse;
import com.project.EMS.dto.requestDto.CreateSalaryRecordRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SalaryRecordService {
    SalaryRecordResponse addSalaryRecord(CreateSalaryRecordRequest createSalaryRecordRequest);
    SalaryRecordResponse getCurrentSalary(Long SalaryRecordId);
   SalaryHistoryPageResponse getSalaryHistory(Long employeeId, int pageNo, int pageSize);

}
