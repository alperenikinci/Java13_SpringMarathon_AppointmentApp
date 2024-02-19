package com.bilgeadam.repository;

import com.bilgeadam.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long> {

    List<Hospital> findAllByCityIgnoreCase(String city);


}
