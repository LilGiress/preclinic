package com.medecineWebApp.Configuration.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SalarySettingsDTO {
    private Long id;
    private double daPercentage;  // DA (in percentage)
    private double hraPercentage; //
}
