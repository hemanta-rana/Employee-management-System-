package com.project.EMS.controller;

import com.project.EMS.dto.ResponseDto.EmployeeResponse;
import com.project.EMS.dto.requestDto.CreateEmployeeRequest;
import com.project.EMS.dto.requestDto.UpdateEmployeeRequest;
import com.project.EMS.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {

   private final EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<EmployeeResponse> createEmployee( @Valid @RequestBody CreateEmployeeRequest createEmployeeRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(createEmployeeRequest));
    }

    @PostMapping("/update/{employeeId}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@Valid @RequestBody UpdateEmployeeRequest updateEmployeeRequest,
                                                           @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployee(updateEmployeeRequest, employeeId));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployee(){
        return ResponseEntity.ok(employeeService.listAllEmployees());
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long employeeId){
        return  ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.deleteEmployeeById(employeeId));
    }



}
