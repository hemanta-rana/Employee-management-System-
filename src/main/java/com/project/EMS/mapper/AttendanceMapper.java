package com.project.EMS.mapper;

import com.project.EMS.dto.ResponseDto.AttendanceResponse;
import com.project.EMS.entity.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {
     @Mapping(source = "employee.id", target = "employeeId")
     AttendanceResponse toAttendanceResponse(Attendance attendance);


}
