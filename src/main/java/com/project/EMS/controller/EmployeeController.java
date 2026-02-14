package com.project.EMS.controller;

import com.project.EMS.dto.requestDto.CreateEmployeeRequest;
import com.project.EMS.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {

   private final EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<?> createEmployee( @Valid @RequestBody CreateEmployeeRequest createEmployeeRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(createEmployeeRequest));
    }

}
