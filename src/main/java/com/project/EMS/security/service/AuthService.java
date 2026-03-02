package com.project.EMS.security.service;

import com.project.EMS.security.dto.requestDto.CreateUserRequest;
import com.project.EMS.security.dto.requestDto.LoginRequest;
import com.project.EMS.security.dto.responseDto.UserResponse;

public interface AuthService {

     UserResponse registerUser(CreateUserRequest createUserRequest);
    UserResponse loginUser(LoginRequest loginRequest);
}
