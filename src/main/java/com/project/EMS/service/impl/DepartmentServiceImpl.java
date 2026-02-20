package com.project.EMS.service.impl;

import com.project.EMS.dto.ResponseDto.DepartmentPageResponse;
import com.project.EMS.dto.ResponseDto.DepartmentResponse;
import com.project.EMS.dto.requestDto.CreateDepartmentRequest;
import com.project.EMS.dto.requestDto.UpdateDepartmentRequest;
import com.project.EMS.entity.Department;
import com.project.EMS.exception.ResourceNotFoundException;
import com.project.EMS.mapper.DepartmentMapper;
import com.project.EMS.projection.DepartmentWithEmployeeCount;
import com.project.EMS.repository.DepartmentRepository;
import com.project.EMS.repository.EmployeeRepository;
import com.project.EMS.service.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentMapper departmentMapper ;

    @Override
    @Transactional
    public DepartmentResponse createDepartment(CreateDepartmentRequest createDepartmentRequest) {

        Department department =departmentMapper.toEntity(createDepartmentRequest);
        department.setCreatedAt(LocalDateTime.now());

        Department savedDepartment = departmentRepository.save(department);
        DepartmentResponse departmentResponse = departmentMapper.mapDepartmentToDepartmentResponse(savedDepartment);
        departmentResponse.setEmployeeCount(employeeRepository.countByDepartmentId(savedDepartment.getId()));

        return departmentResponse;
    }

    @Override
    @Transactional
    public DepartmentResponse updateDepartment(UpdateDepartmentRequest updateDepartmentRequest, Long id) {

        Department department = departmentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Department not found with id: "+id));
        department.setDepartmentName(updateDepartmentRequest.getDepartmentName());
        department.setIsActive(updateDepartmentRequest.getIsActive());

        Department savedDepartment = departmentRepository.save(department);
        DepartmentResponse departmentResponse = departmentMapper.mapDepartmentToDepartmentResponse(savedDepartment);
        departmentResponse.setEmployeeCount(employeeRepository.countByDepartmentId(savedDepartment.getId()));

        return departmentResponse;
    }

    @Override
    public DepartmentPageResponse listAllDepartment(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
       Page<DepartmentWithEmployeeCount> departments = departmentRepository.findAllWithEmployeeCount(pageable);

       List<DepartmentResponse> responses = departmentMapper.toResponseList(departments.getContent());

       return new DepartmentPageResponse(responses,
               departments.getNumber(),
               departments.getSize(),
               departments.getTotalElements(),
               departments.getTotalPages(),
               departments.isLast());
    }


}
