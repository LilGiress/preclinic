package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.CompanySettingDTO;
import com.medecineWebApp.Configuration.models.CompanySetting;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanySettingMapper {
    CompanySettingMapper INSTANCE = Mappers.getMapper(CompanySettingMapper.class);
    CompanySettingDTO companySettingToCompanySettingDTO(CompanySetting companySetting);
    CompanySetting companySettingDTOToCompanySetting(CompanySettingDTO companySettingDTO);
}
