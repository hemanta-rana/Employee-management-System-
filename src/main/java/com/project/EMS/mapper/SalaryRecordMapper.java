package com.project.EMS.mapper;

import com.project.EMS.dto.ResponseDto.SalaryRecordResponse;
import com.project.EMS.dto.requestDto.CreateSalaryRecordRequest;
import com.project.EMS.entity.SalaryRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SalaryRecordMapper {

    SalaryRecordResponse toSalaryRecordResponse(SalaryRecord salaryRecord);
    @Mapping(source = "bonus", target = "bonus", defaultValue = "0")
    @Mapping(source = "deduction", target = "deduction", defaultValue = "0")
    SalaryRecord toEntity(CreateSalaryRecordRequest createSalaryRecordRequest);
}
