package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.CompanySettingDTO;
import com.medecineWebApp.Configuration.models.CompanySetting;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanySettingMapper {
    CompanySettingDTO companySettingToCompanySettingDTO(CompanySetting companySetting);
    @InheritInverseConfiguration
    CompanySetting companySettingDTOToCompanySetting(CompanySettingDTO companySettingDTO);
}
