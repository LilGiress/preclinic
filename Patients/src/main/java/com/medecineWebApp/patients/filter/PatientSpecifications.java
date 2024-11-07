package com.medecineWebApp.patients.filter;

import com.medecineWebApp.patients.enums.PatientStatus;
import com.medecineWebApp.patients.models.Patient;
import org.springframework.data.jpa.domain.Specification;

public class PatientSpecifications {
    public static Specification<Patient> hasPatientId(Long patientId) {
        return ((root, query, criteriaBuilder) ->
                patientId == null ? null :    criteriaBuilder.equal(root.get("patientId"), patientId));
    }

    public static  Specification<Patient> hasDoctorId(Long doctorId) {
        return ((root, query, criteriaBuilder) ->
                doctorId == null ? null :    criteriaBuilder.equal(root.get("doctorId"), doctorId));

    }

    public static Specification<Patient> hasStatus(PatientStatus status) {
        return ((root, query, criteriaBuilder) ->
                status == null ? null :  criteriaBuilder.equal(root.get("status"), status));
    }

//    public static Specification<Patient> hasStatus(String status) {
//        return ((root, query, criteriaBuilder) ->
//                status == null ? null :  criteriaBuilder.equal(root.get("status"), status));
//    }
}
