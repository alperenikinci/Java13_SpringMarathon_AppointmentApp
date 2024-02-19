package com.bilgeadam.mapper;


import com.bilgeadam.dto.request.HospitalCreateRequestDto;
import com.bilgeadam.entity.Hospital;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HospitalMapper {
    HospitalMapper INSTANCE = Mappers.getMapper(HospitalMapper.class);

    Hospital fromHospitalCreateRequestToHospital(final HospitalCreateRequestDto dto);

}
