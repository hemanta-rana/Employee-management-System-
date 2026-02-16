package com.project.EMS.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckInAttendanceRequest {

    @NotNull(message = "Check in time cannot be null ")
    private LocalTime checkIn;

    @NotNull(message = "employee ID cannot be null ")
    private Long employeeId;
}
