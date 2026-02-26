package com.project.EMS.security.service.impl;



import com.project.EMS.security.dto.requestDto.CreateUserRequest;
import com.project.EMS.security.dto.responseDto.UserResponse;
import com.project.EMS.security.service.AuthService;
import com.project.EMS.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Override
    public UserResponse registerUser(CreateUserRequest createUserRequest) {

        return userService.createUser(createUserRequest);

    }
}
