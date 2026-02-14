package com.project.EMS.service.impl;

import com.project.EMS.dto.ResponseDto.EmployeeResponse;
import com.project.EMS.dto.requestDto.CreateEmployeeRequest;
import com.project.EMS.dto.requestDto.UpdateEmployeeRequest;
import com.project.EMS.entity.Department;
import com.project.EMS.entity.Employee;
import com.project.EMS.exception.ResourceNotFoundException;
import com.project.EMS.mapper.EmployeeMapper;

import com.project.EMS.repository.DepartmentRepository;
import com.project.EMS.repository.EmployeeRepository;

import com.project.EMS.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;


    @Override
    @Transactional
    public EmployeeResponse createEmployee(CreateEmployeeRequest createEmployeeRequest) {

        Employee employee = employeeMapper.toEntity(createEmployeeRequest);
        employee.setCreatedAt(LocalDateTime.now());
        Department department = departmentRepository.findById(createEmployeeRequest.getDepartmentId()).orElseThrow(()-> new ResourceNotFoundException("department not found  with id: "+createEmployeeRequest.getDepartmentId()));
        employee.setDepartment(department);

       Employee savedEmployee = employeeRepository.save(employee);
       EmployeeResponse employeeResponse =  employeeMapper.mapEmployeeToEmployeeResponse(savedEmployee);
//
        return employeeResponse;
    }

    @Override
    public EmployeeResponse updateEmployee(UpdateEmployeeRequest updateEmployeeRequest, Long employeeId) {



        return null;
    }

    @Override
    public List<EmployeeResponse> listAllEmployees() {
        return List.of();
    }

    @Override
    public EmployeeResponse getEmployeeById(Long employeeId) {
        return null;
    }
}
