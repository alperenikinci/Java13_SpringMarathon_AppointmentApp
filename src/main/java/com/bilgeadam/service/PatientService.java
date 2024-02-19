package com.bilgeadam.service;

import com.bilgeadam.dto.request.PatientCreateRequestDto;
import com.bilgeadam.entity.Patient;
import com.bilgeadam.mapper.PatientMapper;
import com.bilgeadam.repository.PatientRepository;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;


@Service
public class PatientService extends ServiceManager<Patient,Long> {

    private final PatientRepository patientRepository;


    public PatientService(PatientRepository patientRepository) {
        super(patientRepository);
        this.patientRepository = patientRepository;
    }

    public Patient createPatient(PatientCreateRequestDto dto){
        Patient patient = PatientMapper.INSTANCE.fromCreateRequestToPatient(dto);
        patient.setAge(Period.between(dto.getDateOfBirth(), LocalDate.now()).getYears());
        return save(patient);
    }


    public Optional<Patient> findByTc(String tc) {
        return patientRepository.findByTc(tc);
    }
}
