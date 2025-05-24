package com.medecineWebApp.patients.mapper;

import com.medecineWebApp.patients.dto.FavoriteDTO;
import com.medecineWebApp.patients.models.Favorite;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FavoriteMapper {
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    FavoriteDTO toFavoriteDTO(Favorite favorite);
    @InheritInverseConfiguration
    Favorite toFavorite(FavoriteDTO favoriteDTO);
}
