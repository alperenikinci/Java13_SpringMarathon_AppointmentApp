package com.bilgeadam.mapper;


import com.bilgeadam.dto.request.AppointmentWithPatientRegistrationRequestDto;
import com.bilgeadam.dto.request.PatientCreateRequestDto;
import com.bilgeadam.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);


    Patient fromCreateRequestToPatient(final PatientCreateRequestDto dto);

    PatientCreateRequestDto fromAppointmentWithRegistrationToPatientCreateRequest(final AppointmentWithPatientRegistrationRequestDto dto);


}
