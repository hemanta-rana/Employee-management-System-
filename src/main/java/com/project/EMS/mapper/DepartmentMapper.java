package com.project.EMS.mapper;

import com.project.EMS.dto.ResponseDto.DepartmentResponse;
import com.project.EMS.dto.requestDto.CreateDepartmentRequest;
import com.project.EMS.entity.Department;
import com.project.EMS.projection.DepartmentWithEmployeeCount;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentResponse mapDepartmentToDepartmentResponse(Department department);

    DepartmentResponse toResponse(DepartmentWithEmployeeCount projection);

    List<DepartmentResponse> toResponseList(
            List<DepartmentWithEmployeeCount> projections
    );

    Department toEntity(CreateDepartmentRequest createDepartmentRequest);
}
