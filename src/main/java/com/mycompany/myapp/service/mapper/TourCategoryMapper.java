package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Tour;
import com.mycompany.myapp.domain.TourCategory;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.service.dto.TourCategoryDTO;
import com.mycompany.myapp.service.dto.TourDTO;
import com.mycompany.myapp.service.dto.UserDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TourCategory} and its DTO {@link TourCategoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface TourCategoryMapper extends EntityMapper<TourCategoryDTO, TourCategory> {
    @Mapping(target = "createdBy", source = "createdBy", qualifiedByName = "userLogin")
    @Mapping(target = "parent", source = "parent", qualifiedByName = "tourCategoryCode")
    @Mapping(target = "tours", source = "tours", qualifiedByName = "tourCodeSet")
    TourCategoryDTO toDto(TourCategory s);

    @Mapping(target = "tours", ignore = true)
    @Mapping(target = "removeTour", ignore = true)
    TourCategory toEntity(TourCategoryDTO tourCategoryDTO);

    @Named("tourCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TourDTO toDtoTourCode(Tour tour);

    @Named("tourCodeSet")
    default Set<TourDTO> toDtoTourCodeSet(Set<Tour> tour) {
        return tour.stream().map(this::toDtoTourCode).collect(Collectors.toSet());
    }

    @Named("tourCategoryCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TourCategoryDTO toDtoTourCategoryCode(TourCategory tourCategory);

    @Named("userLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserDTO toDtoUserLogin(User user);
}
