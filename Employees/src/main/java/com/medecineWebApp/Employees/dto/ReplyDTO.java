package com.medecineWebApp.Employees.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO extends AuditableDTO {
    private Long id;
    private String replyText;
    private LocalDateTime repliedDate;
    private ReviewDTO review;
}
