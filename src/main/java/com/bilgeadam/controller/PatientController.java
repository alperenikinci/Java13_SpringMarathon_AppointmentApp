package com.bilgeadam.controller;

import com.bilgeadam.dto.request.PatientCreateRequestDto;
import com.bilgeadam.entity.Patient;
import com.bilgeadam.mapper.PatientMapper;
import com.bilgeadam.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;

import static com.bilgeadam.constant.RestApiUrls.*;

@RestController
@RequestMapping(PATIENT)
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;


    @PostMapping(CREATE)
    public ResponseEntity<Patient> createPatient(@RequestBody PatientCreateRequestDto dto){
        return ResponseEntity.ok(patientService.createPatient(dto));
    }

}
