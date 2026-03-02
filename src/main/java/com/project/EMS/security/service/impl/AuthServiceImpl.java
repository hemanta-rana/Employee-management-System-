package com.project.EMS.security.service.impl;



import com.project.EMS.entity.User;
import com.project.EMS.exception.InvalidCredentialsException;
import com.project.EMS.exception.ResourceNotFoundException;
import com.project.EMS.repository.UserRepository;
import com.project.EMS.security.dto.requestDto.CreateUserRequest;
import com.project.EMS.security.dto.requestDto.LoginRequest;
import com.project.EMS.security.dto.responseDto.UserResponse;
import com.project.EMS.security.mapper.UserMapper;
import com.project.EMS.security.service.AuthService;
import com.project.EMS.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponse registerUser(CreateUserRequest createUserRequest) {

        return userService.createUser(createUserRequest);

    }

    @Override
    public UserResponse loginUser(LoginRequest loginRequest) {
        if (loginRequest== null || loginRequest.getUsername().isBlank() || loginRequest.getPassword().isBlank()){
            throw new IllegalArgumentException("username or password cannot be blank ");
        }
        User user= userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(()-> new ResourceNotFoundException("username not found"));
        if (!user.getPassword().equals(passwordEncoder.encode(loginRequest.getPassword()))){
            throw new InvalidCredentialsException("incorrect password or username");
        }
        return userMapper.toUserResponse(user);



    }


}
