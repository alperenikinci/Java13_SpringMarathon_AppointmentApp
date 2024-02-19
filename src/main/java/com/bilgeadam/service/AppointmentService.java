package com.bilgeadam.service;

import com.bilgeadam.dto.request.AppointmentCreateRequestDto;
import com.bilgeadam.dto.request.AppointmentWithPatientRegistrationRequestDto;
import com.bilgeadam.dto.request.PatientCreateRequestDto;
import com.bilgeadam.entity.Appointment;
import com.bilgeadam.entity.Doctor;
import com.bilgeadam.entity.Hospital;
import com.bilgeadam.entity.Patient;
import com.bilgeadam.mapper.PatientMapper;
import com.bilgeadam.repository.AppointmentRepository;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService extends ServiceManager<Appointment,Long> {

    private final AppointmentRepository appointmentRepository;
    private final PatientService patientService;
    private final HospitalService hospitalService;
    private final DoctorService doctorService;

    public AppointmentService(AppointmentRepository appointmentRepository, PatientService patientService, HospitalService hospitalService, DoctorService doctorService) {
        super(appointmentRepository);
        this.appointmentRepository = appointmentRepository;
        this.patientService = patientService;
        this.hospitalService = hospitalService;
        this.doctorService = doctorService;
    }

    public Appointment newAppointment(AppointmentCreateRequestDto dto){
        List<Appointment> appointmentList = appointmentRepository.findAllByAppointmentDateAndDoctorId(dto.getAppointmentDate(),dto.getDoctorId());
        Optional<Patient> patient = patientService.findByTc(dto.getTc());
        if(patient.isPresent()){
            for (Appointment appointment: appointmentList){
                if(appointment.getAppointmentTime().equals(dto.getAppointmentTime().getTime())){
                    if(appointment.getIsAppointmentTaken()){
                        throw new RuntimeException("Bu randevu daha once baska bir hasta tarafindan alinmis.");
                    } else {
                        appointment.setIsAppointmentTaken(true);
                        appointment.setPatientId(patient.get().getId());
                        return save(appointment);
                    }
                }
            }
        } else {
            throw new RuntimeException("Hasta bulunamadi...");
        }
        throw new RuntimeException("Uygun randevu bulunamadi...");
    }

    public Appointment newAppointmentWithoutRegistration(AppointmentWithPatientRegistrationRequestDto dto) {
        List<Appointment> appointmentList = appointmentRepository.findAllByAppointmentDateAndDoctorId(dto.getAppointmentDate(), dto.getDoctorId());

        if (appointmentList.isEmpty()) {
            throw new RuntimeException("Doktor ya da randevu bulunamadi.");
        }

        for (Appointment appointment : appointmentList) {
            if (appointment.getAppointmentTime().equals(dto.getAppointmentTime().getTime())) {
                if (appointment.getIsAppointmentTaken()) {
                    throw new RuntimeException("Bu randevu zaten alınmıs");
                } else {

                    PatientCreateRequestDto patientCreateRequestDto = PatientMapper.INSTANCE.fromAppointmentWithRegistrationToPatientCreateRequest(dto);
                    Patient patient = patientService.createPatient(patientCreateRequestDto);
                    appointment.setIsAppointmentTaken(true);
                    appointment.setPatientId(patient.getId());
                    return save(appointment);
                }
            }
        }

        throw new RuntimeException("Randevu bulunamadi.");
    }












    public Boolean generateAppointmentsForWeek() {
        List<Appointment> appointments = new ArrayList<>();
        List<Hospital> allHospitals = hospitalService.findAll();

        String[] timeSlots = {
                "09:00-09:30", "09:30-10:00", "10:00-10:30", "10:30-11:00",
                "11:00-11:30", "11:30-12:00", "12:00-12:30", "13:30-14:00",
                "14:00-14:30", "14:30-15:00", "15:00-15:30", "15:30-16:00",
                "16:00-16:30", "16:30-17:00"
        };

        for (Hospital hospital : allHospitals) {
            List<Doctor> doctorsInHospital = doctorService.findAllByHospital(hospital.getId());

            for (Doctor doctor : doctorsInHospital) {
                for (int i = 0; i < 7; i++) {
                    LocalDate currentDate = LocalDate.now().plusDays(i);

                    if (currentDate.getDayOfWeek() == DayOfWeek.SATURDAY || currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                        continue;
                    }

                    List<Appointment> dailyAppointments = generateAppointmentsForDay(currentDate, timeSlots, doctor.getId(), hospital.getId());
                    appointments.addAll(dailyAppointments);
                }
            }
        }
        saveAll(appointments);

        return true;
    }

    public List<Appointment> generateAppointmentsForDay(LocalDate date, String[] timeSlots, Long doctorId, Long hospitalId) {
        List<Appointment> appointments = new ArrayList<>();
        for (String timeSlot : timeSlots) {
            Appointment appointment = Appointment.builder()
                    .appointmentDate(date)
                    .appointmentTime(timeSlot)
                    .doctorId(doctorId)
                    .hospitalId(hospitalId)
                    .build();
            appointments.add(appointment);
        }
        return appointments;
    }

    public List<Appointment> findAllByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public List<Appointment> findAllByDateAndDoctorId(LocalDate date, Long doctorId) {
        return appointmentRepository.findAllByAppointmentDateAndDoctorId(date,doctorId);
    }

    public List<Appointment> findAllAvailableByDateAndDoctorId(LocalDate date, Long doctorId) {
        return appointmentRepository.findAllAvailableByDateAndDoctorId(date,doctorId);
    }
}
