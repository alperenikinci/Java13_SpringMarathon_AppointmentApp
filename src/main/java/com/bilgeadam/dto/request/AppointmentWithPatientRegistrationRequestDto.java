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
public class AppointmentWithPatientRegistrationRequestDto {

    private LocalDate appointmentDate;
    private EAppointmentTimeSlots appointmentTime;
    private String name;
    private String surname;
    private String tc;
    private LocalDate dateOfBirth;
    private Long doctorId;
}
