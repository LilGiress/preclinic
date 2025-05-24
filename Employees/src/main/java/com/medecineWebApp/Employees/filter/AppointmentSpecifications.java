package com.medecineWebApp.Employees.filter;

import com.medecineWebApp.Employees.models.Appointment;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import org.springframework.data.jpa.domain.Specification;

public class AppointmentSpecifications {
    public static Specification<Appointment> byPatientId(Long patient) {
        return ((root, query, criteriaBuilder) ->
                patient == null ? null :    criteriaBuilder.equal(root.get("patient"), patient));

    }
    public static Specification<Appointment> byDoctorId(Doctor doctor) {
        return ((root, query, criteriaBuilder) ->
                doctor.getId() == null ? null :    criteriaBuilder.equal(root.get("doctor"), doctor.getId()));
    }
    public static Specification<Appointment> hasDate(String date){
        return ((root, query, criteriaBuilder) ->
                date == null ? null :    criteriaBuilder.equal(root.get("date"), date));
    }
}
