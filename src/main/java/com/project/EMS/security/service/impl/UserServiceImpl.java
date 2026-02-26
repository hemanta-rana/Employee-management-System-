package com.project.EMS.security.service.impl;

import com.project.EMS.entity.User;
import com.project.EMS.exception.DuplicateResourceException;
import com.project.EMS.security.dto.requestDto.UpdateUserRequest;
import com.project.EMS.security.dto.responseDto.UserResponse;
import com.project.EMS.security.dto.requestDto.CreateUserRequest;
import com.project.EMS.repository.UserRepository;
import com.project.EMS.security.mapper.UserMapper;
import com.project.EMS.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
     
    @Override
    @Transactional
    public UserResponse createUser(CreateUserRequest createUserRequest) {
        if (createUserRequest.getEmail() == null || createUserRequest.getEmail().isBlank()) throw new IllegalArgumentException("Email cannot be blank ");
        if ( userRepository.existsByUsername(createUserRequest.getUsername())) throw  new DuplicateResourceException("User already exist with username "+createUserRequest.getUsername());
        if (userRepository.existsByEmail(createUserRequest.getEmail())) throw new DuplicateResourceException("Email already exist with email: "+ createUserRequest.getEmail());

        User user = new User();
        user.setUsername(createUserRequest.getUsername());
        user.setEmail(createUserRequest.getEmail());
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user.setLastLogin(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        return userMapper.toUserResponse(savedUser);


    }

    @Override
    public UserResponse getUserByEmail(String email) {
        return null;
    }

    @Override
    public UserResponse updateUser(UpdateUserRequest updateUserRequest) {
        return null;
    }

    @Override
    public UserResponse deleteUser(Long userId) {
        return null;
    }

    @Override
    public UserResponse getUserById(Long userId) {
        return null;
    }
}
