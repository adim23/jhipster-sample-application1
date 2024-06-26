package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Tag;
import com.mycompany.myapp.domain.Tour;
import com.mycompany.myapp.domain.TourExtra;
import com.mycompany.myapp.domain.TourExtraCategory;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.service.dto.TagDTO;
import com.mycompany.myapp.service.dto.TourDTO;
import com.mycompany.myapp.service.dto.TourExtraCategoryDTO;
import com.mycompany.myapp.service.dto.TourExtraDTO;
import com.mycompany.myapp.service.dto.UserDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TourExtra} and its DTO {@link TourExtraDTO}.
 */
@Mapper(componentModel = "spring")
public interface TourExtraMapper extends EntityMapper<TourExtraDTO, TourExtra> {
    @Mapping(target = "createdBy", source = "createdBy", qualifiedByName = "userLogin")
    @Mapping(target = "tags", source = "tags", qualifiedByName = "tagCodeSet")
    @Mapping(target = "categories", source = "categories", qualifiedByName = "tourExtraCategoryCodeSet")
    @Mapping(target = "tours", source = "tours", qualifiedByName = "tourCodeSet")
    TourExtraDTO toDto(TourExtra s);

    @Mapping(target = "removeTags", ignore = true)
    @Mapping(target = "removeCategory", ignore = true)
    @Mapping(target = "tours", ignore = true)
    @Mapping(target = "removeTours", ignore = true)
    TourExtra toEntity(TourExtraDTO tourExtraDTO);

    @Named("userLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserDTO toDtoUserLogin(User user);

    @Named("tagCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TagDTO toDtoTagCode(Tag tag);

    @Named("tagCodeSet")
    default Set<TagDTO> toDtoTagCodeSet(Set<Tag> tag) {
        return tag.stream().map(this::toDtoTagCode).collect(Collectors.toSet());
    }

    @Named("tourExtraCategoryCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TourExtraCategoryDTO toDtoTourExtraCategoryCode(TourExtraCategory tourExtraCategory);

    @Named("tourExtraCategoryCodeSet")
    default Set<TourExtraCategoryDTO> toDtoTourExtraCategoryCodeSet(Set<TourExtraCategory> tourExtraCategory) {
        return tourExtraCategory.stream().map(this::toDtoTourExtraCategoryCode).collect(Collectors.toSet());
    }

    @Named("tourCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TourDTO toDtoTourCode(Tour tour);

    @Named("tourCodeSet")
    default Set<TourDTO> toDtoTourCodeSet(Set<Tour> tour) {
        return tour.stream().map(this::toDtoTourCode).collect(Collectors.toSet());
    }
}
