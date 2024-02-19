package com.bilgeadam.service;

import com.bilgeadam.entity.Doctor;
import com.bilgeadam.repository.DoctorRepository;
import com.bilgeadam.utility.ServiceManager;
import com.bilgeadam.utility.enums.EMedicalSpecialty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService extends ServiceManager<Doctor,Long> {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        super(doctorRepository);
        this.doctorRepository = doctorRepository;
    }


    public List<Doctor> getAllBySpeciality(EMedicalSpecialty specialty) {
        return doctorRepository.findByMedicalSpecialty(specialty);
    }

    public List<Doctor> findAllByHospital(Long hospitalId) {
        return doctorRepository.findByHospitalId(hospitalId);
    }

    public List<Doctor> findByHospitalIdAndMedicalSpecialty(Long hospitalId,EMedicalSpecialty medicalSpecialty){
        return doctorRepository.findByHospitalIdAndMedicalSpecialty(hospitalId,medicalSpecialty);
    }


}
