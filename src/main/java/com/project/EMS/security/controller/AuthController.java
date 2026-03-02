package com.project.EMS.security.controller;

import com.project.EMS.security.dto.requestDto.CreateUserRequest;
import com.project.EMS.security.dto.requestDto.LoginRequest;
import com.project.EMS.security.dto.responseDto.UserResponse;
import com.project.EMS.security.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody CreateUserRequest createUserRequest){

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registerUser(createUserRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.loginUser(loginRequest));
    }

}
