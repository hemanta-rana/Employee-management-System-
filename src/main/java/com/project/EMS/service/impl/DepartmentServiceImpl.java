package com.project.EMS.service.impl;

import com.project.EMS.dto.ResponseDto.DepartmentResponse;
import com.project.EMS.dto.requestDto.CreateDepartmentRequest;
import com.project.EMS.dto.requestDto.UpdateDepartmentRequest;
import com.project.EMS.entity.Department;
import com.project.EMS.repository.DepartmentRepository;
import com.project.EMS.service.DepartmentService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponse createDepartment(CreateDepartmentRequest createDepartmentRequest) {
       if (createDepartmentRequest == null) throw new IllegalArgumentException("cannot be null");
        Department department = new Department();
        department.setDepartmentName(createDepartmentRequest.getDepartmentName());
        department.setCreatedAt(createDepartmentRequest.getCreatedAt());
        department.setIsActive(createDepartmentRequest.getIsActive());
        department.setEmployees(department.getEmployees());

        Department savedDepartment = departmentRepository.save(department);
        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setId(savedDepartment.getId());
        departmentResponse.setDepartmentName(savedDepartment.getDepartmentName());
        departmentResponse.setEmployees(savedDepartment.getEmployees());
        departmentResponse.setCreatedAt(savedDepartment.getCreatedAt());
        departmentResponse.setIsActive(savedDepartment.getIsActive());

        return departmentResponse;
    }

    @Override
    public DepartmentResponse updateDepartment(UpdateDepartmentRequest updateDepartmentRequest, Long id) {
        if (updateDepartmentRequest == null) throw new IllegalArgumentException("cannot be null");
        Department department = departmentRepository.findById(id).orElseThrow(()-> new RuntimeException("id not found "));
        department.setId(updateDepartmentRequest.getId());
        department.setDepartmentName(updateDepartmentRequest.getDepartmentName());
        department.setIsActive(updateDepartmentRequest.getIsActive());

        Department savedDepartment = departmentRepository.save(department);

        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setId(savedDepartment.getId());
        departmentResponse.setDepartmentName(savedDepartment.getDepartmentName());
        departmentResponse.setIsActive(savedDepartment.getIsActive());
        departmentResponse.setCreatedAt(savedDepartment.getCreatedAt());
        departmentResponse.setEmployees(savedDepartment.getEmployees());

        return departmentResponse;
    }

}
