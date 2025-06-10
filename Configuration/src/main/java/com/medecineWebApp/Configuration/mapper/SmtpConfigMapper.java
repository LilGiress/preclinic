package com.medecineWebApp.Configuration.mapper;


import com.medecineWebApp.Configuration.dto.SmtpConfigDTO;
import com.medecineWebApp.Configuration.models.setting.SmtpConfig;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface SmtpConfigMapper {
    SmtpConfigDTO smtpConfigToSmtpConfigDTO(SmtpConfig smtpConfig);
    @InheritInverseConfiguration
    SmtpConfig smtpConfigDTOToSmtpConfig(SmtpConfigDTO smtpConfig);

}
