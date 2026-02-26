package com.project.EMS.security.service;

import com.project.EMS.security.dto.requestDto.UpdateUserRequest;
import com.project.EMS.security.dto.responseDto.UserResponse;
import com.project.EMS.security.dto.requestDto.CreateUserRequest;


public interface UserService {
    UserResponse createUser(CreateUserRequest createUserRequest);

    UserResponse getUserByEmail(String email);

    UserResponse updateUser(UpdateUserRequest updateUserRequest);

    UserResponse deleteUser(Long userId);

    UserResponse getUserById(Long userId);





}
