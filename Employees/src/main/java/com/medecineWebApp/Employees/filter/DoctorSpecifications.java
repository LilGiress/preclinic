package com.medecineWebApp.Employees.filter;

import com.medecineWebApp.Employees.enums.Gender;
import com.medecineWebApp.Employees.models.doctors.Doctor;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class DoctorSpecifications {
    public static Specification<Doctor> hasGender(Gender gender ) {
        return (((root, query, criteriaBuilder) ->
                gender  == null ? null : criteriaBuilder.equal(root.get("gender"), gender) ));
    }
    public static Specification<Doctor> hasDepartmentId(Long departmentId) {
        return (((root, query, criteriaBuilder) ->
                departmentId == null ? null : criteriaBuilder.equal(root.get("department"), departmentId) ));
    }

    public static Specification<Doctor> hasdoctorBirthday(Date doctorBirthday) {
        return (((root, query, criteriaBuilder) ->
                doctorBirthday == null ? null : criteriaBuilder.equal(root.get("doctorBirthday"), doctorBirthday) ));
    }

}
