package com.medecineWebApp.Configuration.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HolidayDTO {
    private Long id;
    private String name;
    private LocalDate date;
}
