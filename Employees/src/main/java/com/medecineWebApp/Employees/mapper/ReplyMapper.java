package com.medecineWebApp.Employees.mapper;

import com.medecineWebApp.Employees.dto.ReplyDTO;
import com.medecineWebApp.Employees.models.Reply;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReplyMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    ReplyDTO replyToReplyDTO(Reply reply);
    @InheritInverseConfiguration
    Reply replyDTOToReply(ReplyDTO replyDTO);
}
