package com.project.EMS.service.impl;

import com.project.EMS.dto.ResponseDto.EmployeeResponse;
import com.project.EMS.dto.ResponseDto.EmployeesPageResponse;
import com.project.EMS.dto.requestDto.CreateEmployeeRequest;
import com.project.EMS.dto.requestDto.UpdateEmployeeRequest;
import com.project.EMS.entity.Department;
import com.project.EMS.entity.Employee;
import com.project.EMS.exception.DuplicateResourceException;
import com.project.EMS.exception.ResourceNotFoundException;
import com.project.EMS.mapper.EmployeeMapper;

import com.project.EMS.repository.DepartmentRepository;
import com.project.EMS.repository.EmployeeRepository;

import com.project.EMS.service.EmployeeService;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        if (employeeRepository.existsByEmail(createEmployeeRequest.getEmail())) throw new DuplicateResourceException("Email already exists ");
        Employee employee = employeeMapper.toEntity(createEmployeeRequest);
        employee.setCreatedAt(LocalDateTime.now());
        Department department = departmentRepository.findById(createEmployeeRequest.getDepartmentId()).orElseThrow(()-> new ResourceNotFoundException("department not found  with id: "+createEmployeeRequest.getDepartmentId()));
        employee.setDepartment(department);

       Employee savedEmployee = employeeRepository.save(employee);
       return  employeeMapper.mapEmployeeToEmployeeResponse(savedEmployee);
    }

    @Override
    @Transactional
    public EmployeeResponse updateEmployee(UpdateEmployeeRequest updateEmployeeRequest, Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee not found with ID: "+employeeId));
        if (employeeRepository.existsByEmail(updateEmployeeRequest.getEmail())) throw new DuplicateResourceException("Email already in user ");
        employee.setName(updateEmployeeRequest.getName());
        employee.setEmail(updateEmployeeRequest.getEmail());
        employee.setPhone(updateEmployeeRequest.getPhone());

        Department department = departmentRepository.findById(updateEmployeeRequest.getDepartmentId()).orElseThrow(()-> new ResourceNotFoundException("Department not found ID: "+updateEmployeeRequest.getDepartmentId()));
        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.mapEmployeeToEmployeeResponse(savedEmployee);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeesPageResponse listAllEmployees(int pageNo, int pageSize, String sortBy, String sortDir, String keyWord) {

        Sort.Direction direction = Sort.Direction.fromString(sortDir);
        Sort sort = Sort.by(direction, sortBy);

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Employee> employeePage = null;
        if (keyWord==null || keyWord.isBlank()){
           employeePage = employeeRepository.findByIsActiveTrue(pageable);
        }else {
            employeePage = employeeRepository.findByNameContainingIgnoreCaseAndIsActiveTrue(keyWord, pageable);
        }

        List<EmployeeResponse> employees = employeePage.
                getContent().
                stream().
                map(employeeMapper::mapEmployeeToEmployeeResponse).
                toList();

        return new EmployeesPageResponse(
                employees,
                employeePage.getNumber(),
                employeePage.getSize(),
                employeePage.getTotalElements(),
                employeePage.getTotalPages(),
                employeePage.isLast()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponse getEmployeeById(Long employeeId) {
       Employee employee = employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee not found with ID: "+employeeId));

       return employeeMapper.mapEmployeeToEmployeeResponse(employee);
    }

    @Override
    @Transactional
    public void deleteEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("Employee not found with ID:"+employeeId));
        employee.setIsActive(false);
        employeeRepository.save(employee);
    }


}
