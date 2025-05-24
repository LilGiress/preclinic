package com.medecineWebApp.Employees.mapper;

import com.medecineWebApp.Employees.dto.ReviewDTO;
import com.medecineWebApp.Employees.models.Review;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    ReviewDTO reviewToReviewDTO(Review review);
    @InheritInverseConfiguration
    Review reviewDTOToReview(ReviewDTO reviewDTO);
}
