package com.bilgeadam.utility;

import com.bilgeadam.entity.Doctor;
import com.bilgeadam.entity.Hospital;
import com.bilgeadam.service.DoctorService;
import com.bilgeadam.service.HospitalService;
import com.bilgeadam.utility.enums.EMedicalSpecialty;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataImpl implements ApplicationRunner {

    private final HospitalService hospitalService;
    private final DoctorService doctorService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createSampleData();
    }

    private void createSampleData() {
        createHospitals();
        createDoctors();
    }

    private void createHospitals() {
        List<Hospital> hospitals = Arrays.asList(
                Hospital.builder().name("Hospital1").city("Ankara").build(),
                Hospital.builder().name("Hospital2").city("Izmir").build(),
                Hospital.builder().name("Hospital3").city("Istanbul").build()
        );

        hospitalService.saveAll(hospitals);
    }


    private void createDoctors() {
        List<EMedicalSpecialty> specialties = Arrays.asList(EMedicalSpecialty.values());

        hospitalService.findAll().forEach(hospital -> {
            specialties.forEach(specialty -> {
                createDoctorsForHospitalAndSpecialty(hospital, specialty);
            });
        });
    }

    private void createDoctorsForHospitalAndSpecialty(Hospital hospital, EMedicalSpecialty specialty) {
        List<Doctor> doctors = Arrays.asList(
                Doctor.builder().name("Dr1").surname("Surname1").tc(generateRandomTC()).hospitalId(hospital.getId()).medicalSpecialty(specialty).build(),
                Doctor.builder().name("Dr2").surname("Surname2").tc(generateRandomTC()).hospitalId(hospital.getId()).medicalSpecialty(specialty).build()
                // Add more doctors as needed
        );

        doctorService.saveAll(doctors);
    }

    private String generateRandomTC() {
        StringBuilder tcBuilder = new StringBuilder("1"); // Start with '1' as the first digit

        for (int i = 0; i < 10; i++) {
            int digit = (int) (Math.random() * 10); // Generate a random digit between 0 and 9
            tcBuilder.append(digit);
        }

        return tcBuilder.toString();
    }

}
