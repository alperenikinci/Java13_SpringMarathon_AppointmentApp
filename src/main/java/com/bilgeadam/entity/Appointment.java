package com.bilgeadam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "tbl_appointment")
@Entity
public class Appointment extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate appointmentDate;
    private String appointmentTime;
    @Builder.Default
    private Boolean isAppointmentDone = false;
    @Builder.Default
    private Boolean isAppointmentTaken = false;
    private Long doctorId;
    private Long patientId;
    private Long hospitalId;

}
