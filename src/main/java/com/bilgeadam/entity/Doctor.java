package com.bilgeadam.entity;

import com.bilgeadam.utility.enums.EMedicalSpecialty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "tbl_doctor")
@Entity
public class Doctor extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String tc;
    private Long hospitalId;
    @Enumerated(EnumType.STRING)
    private EMedicalSpecialty medicalSpecialty;


}
