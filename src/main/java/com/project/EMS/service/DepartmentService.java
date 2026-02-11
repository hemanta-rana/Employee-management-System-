package com.project.EMS.service;

import com.project.EMS.dto.ResponseDto.DepartmentResponse;
import com.project.EMS.dto.requestDto.CreateDepartmentRequest;
import com.project.EMS.dto.requestDto.UpdateDepartmentRequest;

public interface DepartmentService {
    DepartmentResponse createDepartment(CreateDepartmentRequest createDepartmentRequest);
    DepartmentResponse updateDepartment(UpdateDepartmentRequest updateDepartmentRequest, Long id);
}
