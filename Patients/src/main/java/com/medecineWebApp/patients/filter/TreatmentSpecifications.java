package com.medecineWebApp.patients.filter;

import com.medecineWebApp.patients.enums.TreatmentStatus;
import com.medecineWebApp.patients.models.Treatment;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TreatmentSpecifications {
    public static Specification<Treatment> treatmentNameContains(String description){
        return ((root, query, criteriaBuilder) ->
                description == null ? null :  criteriaBuilder.equal(root.get("description"), description));
    }

    public static Specification<Treatment> treatmentDescriptionContains(String description){
        return ((root, query, criteriaBuilder) ->
                description == null ? null :  criteriaBuilder.equal(root.get("description"), description));

    }

    public static Specification<Treatment> hasDate(LocalDate date){
        return ((root, query, criteriaBuilder) ->
                date == null ? null :  criteriaBuilder.equal(root.get("date"), date));
    }

    public static Specification<Treatment> hasStatus(TreatmentStatus status){
        return ((root, query, criteriaBuilder) ->
                status == null ? null :  criteriaBuilder.equal(root.get("status"), status)
                );
    }

    public static Specification<Treatment> hasDoctorId(Long doctorId){
        return ((root, query, criteriaBuilder) ->
                doctorId == null ? null :  criteriaBuilder.equal(root.get("doctorId"), doctorId));
    }
}
