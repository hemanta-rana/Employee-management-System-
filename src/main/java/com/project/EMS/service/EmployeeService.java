package com.project.EMS.service;

import com.project.EMS.dto.ResponseDto.EmployeeResponse;
import com.project.EMS.dto.ResponseDto.EmployeesPageResponse;
import com.project.EMS.dto.requestDto.CreateEmployeeRequest;
import com.project.EMS.dto.requestDto.UpdateEmployeeRequest;

import java.util.List;


public interface EmployeeService {
    EmployeeResponse createEmployee(CreateEmployeeRequest createEmployeeRequest);
    EmployeeResponse updateEmployee(UpdateEmployeeRequest updateEmployeeRequest, Long employeeId);
    EmployeesPageResponse listAllEmployees(int pageNo, int pageSize, String sortBy, String sortDir, String keyWord);
    EmployeeResponse getEmployeeById(Long employeeId);
    void deleteEmployeeById(Long employeeId);

}
