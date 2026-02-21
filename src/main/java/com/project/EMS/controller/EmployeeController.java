package com.project.EMS.controller;

import com.project.EMS.dto.ResponseDto.EmployeeResponse;
import com.project.EMS.dto.ResponseDto.EmployeesPageResponse;
import com.project.EMS.dto.requestDto.CreateEmployeeRequest;
import com.project.EMS.dto.requestDto.UpdateEmployeeRequest;
import com.project.EMS.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

   private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee( @Valid @RequestBody CreateEmployeeRequest createEmployeeRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(createEmployeeRequest));
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@Valid @RequestBody UpdateEmployeeRequest updateEmployeeRequest,
                                                           @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployee(updateEmployeeRequest, employeeId));
    }

    @GetMapping
    public ResponseEntity<EmployeesPageResponse> getAllEmployee(@RequestParam(required = false, defaultValue = "0") int pageNo,
                                                                @RequestParam(required = false, defaultValue = "10") int pageSize,
                                                                @RequestParam(required = false, defaultValue = "id") String sortBy,
                                                                @RequestParam(required = false, defaultValue = "ASC") String sortDir,
                                                                @RequestParam(required = false) String keyWord){
        return ResponseEntity.ok(employeeService.listAllEmployees(pageNo, pageSize, sortBy, sortDir, keyWord));
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long employeeId){
        return  ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long employeeId){
        employeeService.deleteEmployeeById(employeeId);
        return ResponseEntity.noContent().build();
    }

 

}
