package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Place;
import com.mycompany.myapp.domain.Tour;
import com.mycompany.myapp.domain.TourStep;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.service.dto.PlaceDTO;
import com.mycompany.myapp.service.dto.TourDTO;
import com.mycompany.myapp.service.dto.TourStepDTO;
import com.mycompany.myapp.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TourStep} and its DTO {@link TourStepDTO}.
 */
@Mapper(componentModel = "spring")
public interface TourStepMapper extends EntityMapper<TourStepDTO, TourStep> {
    @Mapping(target = "createdBy", source = "createdBy", qualifiedByName = "userLogin")
    @Mapping(target = "tour", source = "tour", qualifiedByName = "tourCode")
    @Mapping(target = "place", source = "place", qualifiedByName = "placeCode")
    TourStepDTO toDto(TourStep s);

    @Named("userLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserDTO toDtoUserLogin(User user);

    @Named("tourCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TourDTO toDtoTourCode(Tour tour);

    @Named("placeCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    PlaceDTO toDtoPlaceCode(Place place);
}
