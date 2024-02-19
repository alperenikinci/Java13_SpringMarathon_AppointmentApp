package com.bilgeadam.dto.request;

import com.bilgeadam.utility.enums.EAppointmentTimeSlots;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentCreateRequestDto {

    private LocalDate appointmentDate;
    private EAppointmentTimeSlots appointmentTime;
    private String tc;
    private Long doctorId;

}
