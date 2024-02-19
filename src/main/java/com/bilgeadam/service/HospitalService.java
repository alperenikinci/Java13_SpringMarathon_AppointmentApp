package com.bilgeadam.service;


import com.bilgeadam.dto.request.DoctorCreateRequestDto;
import com.bilgeadam.dto.request.HospitalCreateRequestDto;
import com.bilgeadam.entity.Doctor;
import com.bilgeadam.entity.Hospital;
import com.bilgeadam.mapper.DoctorMapper;
import com.bilgeadam.mapper.HospitalMapper;
import com.bilgeadam.repository.HospitalRepository;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService extends ServiceManager<Hospital,Long> {

    private final HospitalRepository hospitalRepository;
    private final DoctorService doctorService;

    public HospitalService(HospitalRepository hospitalRepository, DoctorService doctorService) {
        super(hospitalRepository);
        this.hospitalRepository = hospitalRepository;
        this.doctorService = doctorService;
    }

    public Hospital createHospital(HospitalCreateRequestDto dto) {
        return save(HospitalMapper.INSTANCE.fromHospitalCreateRequestToHospital(dto));
    }

    public Doctor createDoctor(DoctorCreateRequestDto dto) {
        return doctorService.save(DoctorMapper.INSTANCE.fromDoctorCreateRequestToDoctor(dto));
    }

    public List<Hospital> findAllByCity(String city){
        return hospitalRepository.findAllByCityIgnoreCase(city);
    }


}
