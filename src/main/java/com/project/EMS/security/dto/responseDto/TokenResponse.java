package com.project.EMS.security.dto.responseDto;

import com.project.EMS.security.dto.requestDto.CreateUserRequest;

public record TokenResponse(
        String accessToken,
        String refreshToken,
        long expiresIn,
        String tokenType,
        UserResponse userResponse
) {

    public static TokenResponse of(
            String accessToken,
            String refreshToken,
            long expiresIn,
            UserResponse userResponse
    ) {
        return new TokenResponse(
                accessToken,
                refreshToken,
                expiresIn,
                "Bearer",
               userResponse
        );
    }
}