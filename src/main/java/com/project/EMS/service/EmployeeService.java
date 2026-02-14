package com.project.EMS.service;

import com.project.EMS.dto.ResponseDto.EmployeeResponse;
import com.project.EMS.dto.requestDto.CreateEmployeeRequest;
import com.project.EMS.dto.requestDto.UpdateEmployeeRequest;

import java.util.List;


public interface EmployeeService {
    EmployeeResponse createEmployee(CreateEmployeeRequest createEmployeeRequest);
    EmployeeResponse updateEmployee(UpdateEmployeeRequest updateEmployeeRequest, Long employeeId);
    List<EmployeeResponse> listAllEmployees();
    EmployeeResponse getEmployeeById(Long employeeId);

}
