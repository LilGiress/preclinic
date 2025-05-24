package com.medecineWebApp.Employees.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO extends AuditableDTO {
    private Long id;

    private String content;
    private int rating;

    private Long patientId;

    private DoctorDTO doctor;

    private ReplyDTO reply;

}
