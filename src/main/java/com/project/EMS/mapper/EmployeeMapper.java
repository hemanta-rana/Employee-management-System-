package com.project.EMS.mapper;

import com.project.EMS.dto.ResponseDto.EmployeeResponse;
import com.project.EMS.dto.requestDto.CreateEmployeeRequest;
import com.project.EMS.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface EmployeeMapper {
   @Mapping(source = "department.id", target = "departmentId")
    EmployeeResponse mapEmployeeToEmployeeResponse(Employee employee);

   @Mapping(target = "isActive", source = "isActive", defaultValue = "true")
    Employee toEntity(CreateEmployeeRequest request);
}
