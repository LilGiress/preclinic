package com.medecineWebApp.Configuration.mapper;

import com.medecineWebApp.Configuration.dto.SalarySettingsDTO;
import com.medecineWebApp.Configuration.dto.SmtpConfigDTO;
import com.medecineWebApp.Configuration.models.setting.SmtpConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SmtpConfigMapper {
    SmtpConfigMapper INSTANCE = Mappers.getMapper(SmtpConfigMapper.class);
    SmtpConfigDTO smtpConfigToSmtpConfigDTO(SmtpConfig smtpConfig);
    SmtpConfig smtpConfigDTOToSmtpConfig(SmtpConfigDTO smtpConfig);

}
