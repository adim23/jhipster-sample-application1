package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Destination;
import com.mycompany.myapp.domain.Place;
import com.mycompany.myapp.domain.PlaceCategory;
import com.mycompany.myapp.domain.Tag;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.service.dto.DestinationDTO;
import com.mycompany.myapp.service.dto.PlaceCategoryDTO;
import com.mycompany.myapp.service.dto.PlaceDTO;
import com.mycompany.myapp.service.dto.TagDTO;
import com.mycompany.myapp.service.dto.UserDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Place} and its DTO {@link PlaceDTO}.
 */
@Mapper(componentModel = "spring")
public interface PlaceMapper extends EntityMapper<PlaceDTO, Place> {
    @Mapping(target = "createdBy", source = "createdBy", qualifiedByName = "userLogin")
    @Mapping(target = "tags", source = "tags", qualifiedByName = "tagCodeSet")
    @Mapping(target = "categories", source = "categories", qualifiedByName = "placeCategoryCodeSet")
    @Mapping(target = "destination", source = "destination", qualifiedByName = "destinationCode")
    PlaceDTO toDto(Place s);

    @Mapping(target = "removeTags", ignore = true)
    @Mapping(target = "removeCategory", ignore = true)
    Place toEntity(PlaceDTO placeDTO);

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

    @Named("placeCategoryCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    PlaceCategoryDTO toDtoPlaceCategoryCode(PlaceCategory placeCategory);

    @Named("placeCategoryCodeSet")
    default Set<PlaceCategoryDTO> toDtoPlaceCategoryCodeSet(Set<PlaceCategory> placeCategory) {
        return placeCategory.stream().map(this::toDtoPlaceCategoryCode).collect(Collectors.toSet());
    }

    @Named("destinationCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    DestinationDTO toDtoDestinationCode(Destination destination);
}
