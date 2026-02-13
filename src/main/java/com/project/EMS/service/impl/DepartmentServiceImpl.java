package com.project.EMS.service.impl;

import com.project.EMS.dto.ResponseDto.DepartmentResponse;
import com.project.EMS.dto.requestDto.CreateDepartmentRequest;
import com.project.EMS.dto.requestDto.UpdateDepartmentRequest;
import com.project.EMS.entity.Department;
import com.project.EMS.repository.DepartmentRepository;
import com.project.EMS.repository.EmployeeRepository;
import com.project.EMS.service.DepartmentService;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public DepartmentResponse createDepartment(CreateDepartmentRequest createDepartmentRequest) {

        Department department = new Department();
        department.setDepartmentName(createDepartmentRequest.getDepartmentName());
        department.setIsActive(createDepartmentRequest.getIsActive());


        Department savedDepartment = departmentRepository.save(department);

        return mapToDto(savedDepartment);
    }

    @Override
    @Transactional
    public DepartmentResponse updateDepartment(UpdateDepartmentRequest updateDepartmentRequest, Long id) {

        Department department = departmentRepository.findById(id).orElseThrow(()-> new RuntimeException("id not found "));
        department.setDepartmentName(updateDepartmentRequest.getDepartmentName());
        department.setIsActive(updateDepartmentRequest.getIsActive());

        Department savedDepartment = departmentRepository.save(department);

       return mapToDto(savedDepartment);



    }

    private DepartmentResponse mapToDto(Department department){
        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setId(department.getId());
        departmentResponse.setDepartmentName(department.getDepartmentName());
        departmentResponse.setIsActive(department.getIsActive());
        departmentResponse.setCreatedAt(department.getCreatedAt());
        departmentResponse.setEmployeeCount(employeeRepository.countByDepartmentId(department.getId()));

        return departmentResponse;
    }

}
