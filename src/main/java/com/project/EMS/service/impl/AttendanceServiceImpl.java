package com.project.EMS.service.impl;

import com.project.EMS.dto.ResponseDto.AttendanceResponse;
import com.project.EMS.dto.requestDto.CheckInAttendanceRequest;
import com.project.EMS.dto.requestDto.CheckOutAttendanceRequest;
import com.project.EMS.entity.Attendance;
import com.project.EMS.entity.Employee;
import com.project.EMS.exception.ResourceNotFoundException;
import com.project.EMS.mapper.AttendanceMapper;
import com.project.EMS.repository.AttendanceRepository;
import com.project.EMS.repository.EmployeeRepository;
import com.project.EMS.service.AttendanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public AttendanceResponse markInAttendance(CheckInAttendanceRequest checkInAttendanceRequest) {
       Employee employee=  employeeRepository.findById(checkInAttendanceRequest.getEmployeeId()).orElseThrow(()-> new ResourceNotFoundException("Employee not found with ID; "+checkInAttendanceRequest.getEmployeeId()));
       Attendance attendance = new Attendance();
       attendance.setAttendanceDate(LocalDate.now());
       attendance.setCheckIn(checkInAttendanceRequest.getCheckIn());
       attendance.setEmployee(employee);
       attendance.setCreatedAt(LocalDateTime.now());
       Attendance savedAttendance = attendanceRepository.save(attendance);

       return attendanceMapper.toAttendanceResponse(savedAttendance);

    }

    @Override
    @Transactional
    public AttendanceResponse markCheckOutAttendance(CheckOutAttendanceRequest checkOutAttendanceRequest, Long attendanceId) {
       Attendance attendance = attendanceRepository.findById(attendanceId).orElseThrow(()-> new ResourceNotFoundException("Attendance not found with ID :"+attendanceId));
       attendance.setCheckOut(checkOutAttendanceRequest.getCheckOut());
       Attendance savedAttendance = attendanceRepository.save(attendance);

       return attendanceMapper.toAttendanceResponse(savedAttendance);


    }
}
