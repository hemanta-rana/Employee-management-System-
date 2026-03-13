package com.project.EMS.security.dto.responseDto;

import java.time.ZoneOffset;

public record ApiError(
        int status,
        String error,
        String message,
        String path
) {
    public static ApiError of(int status, String error, String message, String path){
        return new ApiError(status, error, message, path);
    }

}
