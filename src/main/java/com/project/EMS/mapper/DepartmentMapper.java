package com.project.EMS.mapper;

import com.project.EMS.dto.ResponseDto.DepartmentResponse;
import com.project.EMS.entity.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentResponse mapDepartmentToDepartmentResponse(Department department);
}
