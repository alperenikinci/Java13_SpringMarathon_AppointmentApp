package com.bilgeadam.repository;

import com.bilgeadam.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    List<Appointment> findAllByAppointmentDateAndDoctorId(LocalDate date, Long doctorId);
    List<Appointment> findByDoctorId(Long doctorId);

    @Query("SELECT a FROM Appointment a WHERE a.appointmentDate = :date AND a.isAppointmentTaken = false AND a.doctorId = :doctorId")
    List<Appointment> findAllAvailableByDateAndDoctorId(
            @Param("date") LocalDate date,
            @Param("doctorId") Long doctorId
    );
}
