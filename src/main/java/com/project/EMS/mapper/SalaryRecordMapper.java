package com.project.EMS.mapper;

import com.project.EMS.dto.ResponseDto.SalaryRecordResponse;
import com.project.EMS.dto.requestDto.CreateSalaryRecordRequest;
import com.project.EMS.entity.SalaryRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SalaryRecordMapper {


    @Mapping(source =  "employee.id", target = "employeeId")
    SalaryRecordResponse toSalaryRecordResponse(SalaryRecord salaryRecord);

    SalaryRecord toEntity(CreateSalaryRecordRequest createSalaryRecordRequest);
}
