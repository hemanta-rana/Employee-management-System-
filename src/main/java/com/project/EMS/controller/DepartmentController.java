package com.project.EMS.controller;

import com.project.EMS.dto.ResponseDto.DepartmentPageResponse;
import com.project.EMS.dto.ResponseDto.DepartmentResponse;
import com.project.EMS.dto.requestDto.CreateDepartmentRequest;
import com.project.EMS.dto.requestDto.UpdateDepartmentRequest;
import com.project.EMS.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/departments")
public class DepartmentController {

    private  final DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<DepartmentResponse> createDepartment (@Valid @RequestBody CreateDepartmentRequest createDepartmentRequest){
      return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.createDepartment(createDepartmentRequest));
    }

    @PutMapping("{departmentId}")
    public ResponseEntity<DepartmentResponse> updateDepartment(@Valid @RequestBody UpdateDepartmentRequest updateDepartmentRequest, @PathVariable Long departmentId){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.updateDepartment(updateDepartmentRequest, departmentId));
    }

    @GetMapping
    public ResponseEntity<DepartmentPageResponse> getAllDepartment (@RequestParam(defaultValue = "0") int pageNo,
                                                                    @RequestParam(defaultValue = "10") int pageSize){
        return ResponseEntity.ok(departmentService.listAllDepartment(pageNo, pageSize));
    }

}
