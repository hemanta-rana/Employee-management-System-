package com.project.EMS.controller;

import com.project.EMS.dto.ResponseDto.AttendanceResponse;
import com.project.EMS.dto.requestDto.CheckInAttendanceRequest;

import com.project.EMS.dto.requestDto.CheckOutAttendanceRequest;
import com.project.EMS.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
@AllArgsConstructor
public class AttendanceController {
    private final AttendanceService attendanceService;

    @PostMapping("/checkIn")
    public ResponseEntity<AttendanceResponse> checkInAttendance(@Valid @RequestBody CheckInAttendanceRequest checkInAttendanceRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(attendanceService.markInAttendance(checkInAttendanceRequest));
    }

    @PostMapping("/checkOut/{attendanceId}")
    public ResponseEntity<AttendanceResponse> checkOutAttendance(@Valid @RequestBody CheckOutAttendanceRequest checkOutAttendanceRequest, @PathVariable Long attendanceId){
        return ResponseEntity.ok(attendanceService.markCheckOutAttendance(checkOutAttendanceRequest,attendanceId ));
    }
}
