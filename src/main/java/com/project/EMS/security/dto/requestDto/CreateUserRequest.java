package com.project.EMS.security.dto.requestDto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = "username cannot be blank")
    private String username;

    @NotBlank(message = "message cannot be blank")
    private String password;

    @Email
    @NotBlank(message = "email cannot be blank")
    private String email;




}
