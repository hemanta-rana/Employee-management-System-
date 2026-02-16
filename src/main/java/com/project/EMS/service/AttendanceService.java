package com.project.EMS.service;


import com.project.EMS.dto.ResponseDto.AttendanceResponse;
import com.project.EMS.dto.requestDto.CheckInAttendanceRequest;
import com.project.EMS.dto.requestDto.CheckOutAttendanceRequest;


public interface AttendanceService {

    AttendanceResponse markInAttendance(CheckInAttendanceRequest checkInAttendanceRequest);
    AttendanceResponse markCheckOutAttendance(CheckOutAttendanceRequest checkOutAttendanceRequest, Long attendanceId);


}