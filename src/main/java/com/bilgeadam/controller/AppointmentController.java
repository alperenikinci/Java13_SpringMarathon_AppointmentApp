package com.bilgeadam.controller;

import com.bilgeadam.dto.request.AppointmentCreateRequestDto;
import com.bilgeadam.dto.request.AppointmentWithPatientRegistrationRequestDto;
import com.bilgeadam.entity.Appointment;
import com.bilgeadam.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.bilgeadam.constant.RestApiUrls.*;


@RestController
@RequestMapping(APPOINTMENT)
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;


    @PostMapping(value = NEW_APPOINTMENT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Appointment> newAppointment(@RequestBody @ModelAttribute AppointmentCreateRequestDto dto){
        return ResponseEntity.ok(appointmentService.newAppointment(dto));
    }

    @PostMapping(value = NEW_APPOINTMENT_WITHOUT_REGISTRATION, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Appointment> newAppointmentWithoutRegistration(@RequestBody @ModelAttribute AppointmentWithPatientRegistrationRequestDto dto){
        return ResponseEntity.ok(appointmentService.newAppointmentWithoutRegistration(dto));
    }

    @GetMapping("/generate")
    public ResponseEntity<Boolean> generateAppointmentsForWeek(){
        return ResponseEntity.ok(appointmentService.generateAppointmentsForWeek());
    }

    @GetMapping("find-by-doctor")
    public ResponseEntity<List<Appointment>> findAllByDoctorId(@RequestParam Long doctorId) {
        return ResponseEntity.ok(appointmentService.findAllByDoctorId(doctorId));
    }

    @GetMapping("find-by-date-and-doctor")
    public ResponseEntity<List<Appointment>> findAllByDateAndDoctorId(@RequestParam LocalDate date, Long doctorId){
        return ResponseEntity.ok(appointmentService.findAllByDateAndDoctorId(date,doctorId));
    }

    @GetMapping("find-all-available-by-date-and-doctor")
    public ResponseEntity<List<Appointment>> findAllAvailableByDateAndDoctorId(@RequestParam LocalDate date, Long doctorId){
        return ResponseEntity.ok(appointmentService.findAllAvailableByDateAndDoctorId(date,doctorId));
    }

}
