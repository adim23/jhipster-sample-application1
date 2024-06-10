package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Place;
import com.mycompany.myapp.domain.PlaceCategory;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.service.dto.PlaceCategoryDTO;
import com.mycompany.myapp.service.dto.PlaceDTO;
import com.mycompany.myapp.service.dto.UserDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PlaceCategory} and its DTO {@link PlaceCategoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface PlaceCategoryMapper extends EntityMapper<PlaceCategoryDTO, PlaceCategory> {
    @Mapping(target = "createdBy", source = "createdBy", qualifiedByName = "userLogin")
    @Mapping(target = "places", source = "places", qualifiedByName = "placeCodeSet")
    PlaceCategoryDTO toDto(PlaceCategory s);

    @Mapping(target = "places", ignore = true)
    @Mapping(target = "removePlace", ignore = true)
    PlaceCategory toEntity(PlaceCategoryDTO placeCategoryDTO);

    @Named("userLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserDTO toDtoUserLogin(User user);

    @Named("placeCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    PlaceDTO toDtoPlaceCode(Place place);

    @Named("placeCodeSet")
    default Set<PlaceDTO> toDtoPlaceCodeSet(Set<Place> place) {
        return place.stream().map(this::toDtoPlaceCode).collect(Collectors.toSet());
    }
}
