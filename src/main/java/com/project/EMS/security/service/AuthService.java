package com.project.EMS.security.service;

import com.project.EMS.security.dto.requestDto.CreateUserRequest;
import com.project.EMS.security.dto.responseDto.UserResponse;

public interface AuthService {

    public UserResponse registerUser(CreateUserRequest createUserRequest);
}
