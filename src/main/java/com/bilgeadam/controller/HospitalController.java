package com.bilgeadam.controller;

import com.bilgeadam.dto.request.DoctorCreateRequestDto;
import com.bilgeadam.dto.request.HospitalCreateRequestDto;
import com.bilgeadam.entity.Doctor;
import com.bilgeadam.entity.Hospital;
import com.bilgeadam.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constant.RestApiUrls.*;

@RestController
@RequestMapping(HOSPITAL)
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping(CREATE)
    public ResponseEntity<Hospital> createHospital(@RequestBody HospitalCreateRequestDto dto){
        return ResponseEntity.ok(hospitalService.createHospital(dto));
    }

    @PostMapping(value = CREATE_DOCTOR, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Doctor> createDoctor(@RequestBody @ModelAttribute DoctorCreateRequestDto dto){
        return ResponseEntity.ok(hospitalService.createDoctor(dto));
    }

    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Hospital>> findAll(){
        return ResponseEntity.ok(hospitalService.findAll());
    }

    @GetMapping(FIND_ALL_BY_CITY)
    public ResponseEntity<List<Hospital>> findAllByCity(@RequestParam String city){
        return ResponseEntity.ok(hospitalService.findAllByCity(city));
    }
}
