package com.project.EMS.security.service.impl;



import com.project.EMS.entity.User;
import com.project.EMS.repository.UserRepository;
import com.project.EMS.security.dto.requestDto.CreateUserRequest;
import com.project.EMS.security.dto.requestDto.LoginRequest;
import com.project.EMS.security.dto.responseDto.TokenResponse;
import com.project.EMS.security.dto.responseDto.UserResponse;
import com.project.EMS.security.entity.RefreshToken;
import com.project.EMS.security.jwt.JwtService;
import com.project.EMS.security.mapper.UserMapper;
import com.project.EMS.security.repository.RefreshTokenRepository;
import com.project.EMS.security.service.AuthService;
import com.project.EMS.security.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final UserRepository userRepository;
   private final JwtService jwtService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public UserResponse registerUser(CreateUserRequest createUserRequest) {

        return userService.createUser(createUserRequest);

    }

    @Override
    public TokenResponse loginUser(LoginRequest loginRequest) {

       Authentication authentication = authenticate(loginRequest);
        User user = userRepository.findByUsername(loginRequest.username()).orElseThrow(()-> new BadCredentialsException("Invalid username or password "));
        if (!user.isEnabled()) throw new DisabledException("User is disabled ");
        String jti = UUID.randomUUID().toString();
        var refreshTokenObj = RefreshToken.builder()
                .jti(jti)
                .user(user)
                .createdAt(Instant.now())
                .expiredAt(Instant.now().plusSeconds(jwtService.getRefreshTtlSeconds()))
                .revoked(false)
                .build();
        // refresh token info save
        refreshTokenRepository.save(refreshTokenObj);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user,refreshTokenObj.getJti());



        return TokenResponse.of(accessToken, refreshToken, jwtService.getAccessTtlSeconds(), userMapper.toUserResponse(user));


    }

    private Authentication authenticate(LoginRequest loginRequest){

        try{
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        }catch (Exception e){
            throw new BadCredentialsException("Invalid username or password");
        }
    }




}
