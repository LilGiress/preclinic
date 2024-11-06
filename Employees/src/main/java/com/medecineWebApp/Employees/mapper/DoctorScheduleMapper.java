package com.medecineWebApp.Employees.mapper;

import com.medecineWebApp.Employees.dto.DoctorScheduleDTO;
import com.medecineWebApp.Employees.models.doctors.DoctorSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface DoctorScheduleMapper {
    DoctorScheduleMapper INSTANCE = Mappers.getMapper(DoctorScheduleMapper.class);
    DoctorScheduleDTO DoctorScheduleToDoctorScheduleDTO(DoctorSchedule doctorSchedule);
    DoctorSchedule DoctorScheduleDTOToDoctorSchedule(DoctorScheduleDTO doctorScheduleDTO);
    Page<DoctorSchedule> DOCTOR_SCHEDULE_PAGE (Page<DoctorScheduleDTO> doctorSchedulePageDTO);
    Page<DoctorScheduleDTO> DoctorScheduleDTO_PAGE (Page<DoctorSchedule> doctorSchedulePage);
    Optional<DoctorScheduleDTO> DOCTOR_SCHEDULE_DTO_OPTIONAL (Optional<DoctorSchedule> doctorSchedule);
}
