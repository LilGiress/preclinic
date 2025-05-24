package com.medecineWebApp.patients.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecord extends Auditable  {
    private Long id;
    private String description;
    private Date date;
    private Long patientId;
    private Long doctorId;
    private Long serviceId;
    private Long treatmentId;


}
