package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Destination;
import com.mycompany.myapp.domain.Place;
import com.mycompany.myapp.domain.Promotion;
import com.mycompany.myapp.domain.Tag;
import com.mycompany.myapp.domain.Tour;
import com.mycompany.myapp.domain.TourCategory;
import com.mycompany.myapp.domain.TourContent;
import com.mycompany.myapp.domain.TourExtra;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.service.dto.DestinationDTO;
import com.mycompany.myapp.service.dto.PlaceDTO;
import com.mycompany.myapp.service.dto.PromotionDTO;
import com.mycompany.myapp.service.dto.TagDTO;
import com.mycompany.myapp.service.dto.TourCategoryDTO;
import com.mycompany.myapp.service.dto.TourContentDTO;
import com.mycompany.myapp.service.dto.TourDTO;
import com.mycompany.myapp.service.dto.TourExtraDTO;
import com.mycompany.myapp.service.dto.UserDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tour} and its DTO {@link TourDTO}.
 */
@Mapper(componentModel = "spring")
public interface TourMapper extends EntityMapper<TourDTO, Tour> {
    @Mapping(target = "content", source = "content", qualifiedByName = "tourContentCode")
    @Mapping(target = "createdBy", source = "createdBy", qualifiedByName = "userLogin")
    @Mapping(target = "meetingPoint", source = "meetingPoint", qualifiedByName = "placeCode")
    @Mapping(target = "finishPoint", source = "finishPoint", qualifiedByName = "placeCode")
    @Mapping(target = "tourExtras", source = "tourExtras", qualifiedByName = "tourExtraCodeSet")
    @Mapping(target = "tags", source = "tags", qualifiedByName = "tagCodeSet")
    @Mapping(target = "promotions", source = "promotions", qualifiedByName = "promotionCodeSet")
    @Mapping(target = "categories", source = "categories", qualifiedByName = "tourCategoryCodeSet")
    @Mapping(target = "destination", source = "destination", qualifiedByName = "destinationCode")
    @Mapping(target = "defaultCategory", source = "defaultCategory", qualifiedByName = "tourCategoryCode")
    TourDTO toDto(Tour s);

    @Mapping(target = "removeTourExtra", ignore = true)
    @Mapping(target = "removeTags", ignore = true)
    @Mapping(target = "removePromotions", ignore = true)
    @Mapping(target = "removeCategory", ignore = true)
    Tour toEntity(TourDTO tourDTO);

    @Named("tourContentCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TourContentDTO toDtoTourContentCode(TourContent tourContent);

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

    @Named("tourExtraCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TourExtraDTO toDtoTourExtraCode(TourExtra tourExtra);

    @Named("tourExtraCodeSet")
    default Set<TourExtraDTO> toDtoTourExtraCodeSet(Set<TourExtra> tourExtra) {
        return tourExtra.stream().map(this::toDtoTourExtraCode).collect(Collectors.toSet());
    }

    @Named("tagCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TagDTO toDtoTagCode(Tag tag);

    @Named("tagCodeSet")
    default Set<TagDTO> toDtoTagCodeSet(Set<Tag> tag) {
        return tag.stream().map(this::toDtoTagCode).collect(Collectors.toSet());
    }

    @Named("promotionCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    PromotionDTO toDtoPromotionCode(Promotion promotion);

    @Named("promotionCodeSet")
    default Set<PromotionDTO> toDtoPromotionCodeSet(Set<Promotion> promotion) {
        return promotion.stream().map(this::toDtoPromotionCode).collect(Collectors.toSet());
    }

    @Named("tourCategoryCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TourCategoryDTO toDtoTourCategoryCode(TourCategory tourCategory);

    @Named("tourCategoryCodeSet")
    default Set<TourCategoryDTO> toDtoTourCategoryCodeSet(Set<TourCategory> tourCategory) {
        return tourCategory.stream().map(this::toDtoTourCategoryCode).collect(Collectors.toSet());
    }

    @Named("destinationCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    DestinationDTO toDtoDestinationCode(Destination destination);
}
