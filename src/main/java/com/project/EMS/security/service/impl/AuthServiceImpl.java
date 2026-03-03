package com.project.EMS.security.service.impl;



import com.project.EMS.entity.User;
import com.project.EMS.repository.UserRepository;
import com.project.EMS.security.dto.requestDto.CreateUserRequest;
import com.project.EMS.security.dto.requestDto.LoginRequest;
import com.project.EMS.security.dto.responseDto.TokenResponse;
import com.project.EMS.security.dto.responseDto.UserResponse;
import com.project.EMS.security.jwt.JwtService;
import com.project.EMS.security.mapper.UserMapper;
import com.project.EMS.security.service.AuthService;
import com.project.EMS.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final UserRepository userRepository;
   private final JwtService jwtService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserResponse registerUser(CreateUserRequest createUserRequest) {

        return userService.createUser(createUserRequest);

    }

    @Override
    public TokenResponse loginUser(LoginRequest loginRequest) {

       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        User user = userRepository.findByUsername(loginRequest.username()).orElseThrow(()-> new BadCredentialsException("Invalid username or password "));
        if (!user.isEnabled()) throw new DisabledException("User is disabled ");
        String accessToken = jwtService.generateAccessToken(user);

        return TokenResponse.of(accessToken, "", jwtService.getAccessTtlSeconds(), userMapper.toUserResponse(user));






    }


}
