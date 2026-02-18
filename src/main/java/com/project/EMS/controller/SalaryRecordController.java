package com.project.EMS.controller;

import com.project.EMS.dto.ResponseDto.SalaryRecordResponse;
import com.project.EMS.dto.requestDto.CreateSalaryRecordRequest;
import com.project.EMS.service.SalaryRecordService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/salaryRecord/")
public class SalaryRecordController {
    private final SalaryRecordService salaryRecordService;

    @PostMapping("/create")
    public ResponseEntity<SalaryRecordResponse> createSalaryRecord(@Valid @RequestBody CreateSalaryRecordRequest createSalaryRecordRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(salaryRecordService.addSalaryRecord(createSalaryRecordRequest));
    }

    @GetMapping("/current/{employeeId}")
    public ResponseEntity<SalaryRecordResponse> getCurrentSalaryRecord(@PathVariable Long employeeId){
        return ResponseEntity.ok(salaryRecordService.getCurrentSalary(employeeId));
    }

    @GetMapping("/history/{employeeId}")
    public ResponseEntity<List<SalaryRecordResponse>> getAllSalaryRecordOfEmployee(@PathVariable Long employeeId){
        return ResponseEntity.ok(salaryRecordService.getSalaryHistory(employeeId));
    }
}
