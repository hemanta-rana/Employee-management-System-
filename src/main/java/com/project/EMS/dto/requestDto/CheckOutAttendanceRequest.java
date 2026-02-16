package com.project.EMS.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckOutAttendanceRequest {



    @NotNull(message = "check out cannot be null ")
    private LocalTime checkOut;




}
