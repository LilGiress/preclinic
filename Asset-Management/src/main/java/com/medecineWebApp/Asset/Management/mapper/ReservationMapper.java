package com.medecineWebApp.Asset.Management.mapper;

import com.medecineWebApp.Asset.Management.dto.ReservationDTO;
import com.medecineWebApp.Asset.Management.models.Reservation;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper (componentModel = "spring")
public interface ReservationMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    ReservationDTO toReservationDTO(Reservation reservation);
    @InheritInverseConfiguration
    Reservation toReservation(ReservationDTO reservationDTO);
}
