package com.project.EMS.service.impl;

import com.project.EMS.dto.ResponseDto.SalaryHistoryPageResponse;
import com.project.EMS.dto.ResponseDto.SalaryRecordResponse;
import com.project.EMS.dto.requestDto.CreateSalaryRecordRequest;
import com.project.EMS.entity.Employee;
import com.project.EMS.entity.SalaryRecord;
import com.project.EMS.exception.EmployeeNotActiveException;
import com.project.EMS.exception.InvalidSalaryAmountException;
import com.project.EMS.exception.ResourceNotFoundException;
import com.project.EMS.mapper.SalaryRecordMapper;
import com.project.EMS.repository.EmployeeRepository;
import com.project.EMS.repository.SalaryRecordRepository;
import com.project.EMS.service.SalaryRecordService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SalaryRecordServiceImpl implements SalaryRecordService {

    private final SalaryRecordMapper salaryRecordMapper;
    private final SalaryRecordRepository salaryRecordRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public SalaryRecordResponse addSalaryRecord(CreateSalaryRecordRequest createSalaryRecordRequest) {

        if (createSalaryRecordRequest.getBasicSalary().compareTo(BigDecimal.ZERO) <= 0) throw new InvalidSalaryAmountException("Salary must be valid or greater than 0  "+createSalaryRecordRequest.getBasicSalary());
        Employee employee = employeeRepository.findById(createSalaryRecordRequest.getEmployeeId()).orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + createSalaryRecordRequest.getEmployeeId()));
        if (!employee.getIsActive()) throw new EmployeeNotActiveException("Employee not active with ID: "+employee.getId());
        SalaryRecord salaryRecord = salaryRecordMapper.toEntity(createSalaryRecordRequest);
        salaryRecord.setCreatedAt(LocalDateTime.now());
        salaryRecord.setEmployee(employee);

        SalaryRecord savedSalaryRecord = salaryRecordRepository.save(salaryRecord);
        return salaryRecordMapper.toSalaryRecordResponse(savedSalaryRecord);
    }

    @Override
    public SalaryRecordResponse getCurrentSalary(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) throw new ResourceNotFoundException("employee Id not found with ID: "+employeeId);

        SalaryRecord salaryRecord =salaryRecordRepository.findFirstByEmployeeIdOrderByEffectiveDateDesc(employeeId);
        if(salaryRecord ==null) throw new ResourceNotFoundException("Salary record not found for Employee id"+employeeId);
        return salaryRecordMapper.toSalaryRecordResponse(salaryRecord);


    }

    @Override
    public SalaryHistoryPageResponse getSalaryHistory(Long  employeeId, int pageNo, int pageSize){
        if (!employeeRepository.existsById(employeeId)) throw new ResourceNotFoundException("Employee not found with ID: "+employeeId);
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<SalaryRecord> pageResult = salaryRecordRepository.findByEmployeeIdOrderByEffectiveDateDesc(employeeId, pageable);
        List<SalaryRecordResponse> mappedContent = pageResult.getContent().stream()
                .map(salaryRecordMapper::toSalaryRecordResponse)
                .toList();

        return new SalaryHistoryPageResponse(
                mappedContent,
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.getTotalPages(),
                pageResult.isLast()
        );
    }
}
