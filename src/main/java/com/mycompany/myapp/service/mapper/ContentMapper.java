package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Content;
import com.mycompany.myapp.domain.Destination;
import com.mycompany.myapp.domain.ImageFile;
import com.mycompany.myapp.domain.Language;
import com.mycompany.myapp.domain.Menu;
import com.mycompany.myapp.domain.Place;
import com.mycompany.myapp.domain.PlaceCategory;
import com.mycompany.myapp.domain.Promotion;
import com.mycompany.myapp.domain.Tag;
import com.mycompany.myapp.domain.TourCategory;
import com.mycompany.myapp.domain.TourExtra;
import com.mycompany.myapp.domain.TourExtraCategory;
import com.mycompany.myapp.domain.TourStep;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.domain.WebPage;
import com.mycompany.myapp.service.dto.ContentDTO;
import com.mycompany.myapp.service.dto.DestinationDTO;
import com.mycompany.myapp.service.dto.ImageFileDTO;
import com.mycompany.myapp.service.dto.LanguageDTO;
import com.mycompany.myapp.service.dto.MenuDTO;
import com.mycompany.myapp.service.dto.PlaceCategoryDTO;
import com.mycompany.myapp.service.dto.PlaceDTO;
import com.mycompany.myapp.service.dto.PromotionDTO;
import com.mycompany.myapp.service.dto.TagDTO;
import com.mycompany.myapp.service.dto.TourCategoryDTO;
import com.mycompany.myapp.service.dto.TourExtraCategoryDTO;
import com.mycompany.myapp.service.dto.TourExtraDTO;
import com.mycompany.myapp.service.dto.TourStepDTO;
import com.mycompany.myapp.service.dto.UserDTO;
import com.mycompany.myapp.service.dto.WebPageDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Content} and its DTO {@link ContentDTO}.
 */
@Mapper(componentModel = "spring")
public interface ContentMapper extends EntityMapper<ContentDTO, Content> {
    @Mapping(target = "language", source = "language", qualifiedByName = "languageCode")
    @Mapping(target = "createdBy", source = "createdBy", qualifiedByName = "userLogin")
    @Mapping(target = "destination", source = "destination", qualifiedByName = "destinationCode")
    @Mapping(target = "tourCategory", source = "tourCategory", qualifiedByName = "tourCategoryCode")
    @Mapping(target = "place", source = "place", qualifiedByName = "placeCode")
    @Mapping(target = "placeCategory", source = "placeCategory", qualifiedByName = "placeCategoryCode")
    @Mapping(target = "tourExtraCategory", source = "tourExtraCategory", qualifiedByName = "tourExtraCategoryCode")
    @Mapping(target = "tourExtra", source = "tourExtra", qualifiedByName = "tourExtraCode")
    @Mapping(target = "menu", source = "menu", qualifiedByName = "menuCode")
    @Mapping(target = "webPage", source = "webPage", qualifiedByName = "webPageCode")
    @Mapping(target = "tag", source = "tag", qualifiedByName = "tagCode")
    @Mapping(target = "tourStep", source = "tourStep", qualifiedByName = "tourStepCode")
    @Mapping(target = "promotion", source = "promotion", qualifiedByName = "promotionCode")
    @Mapping(target = "imageFile", source = "imageFile", qualifiedByName = "imageFileCode")
    ContentDTO toDto(Content s);

    @Named("languageCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    LanguageDTO toDtoLanguageCode(Language language);

    @Named("userLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserDTO toDtoUserLogin(User user);

    @Named("destinationCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    DestinationDTO toDtoDestinationCode(Destination destination);

    @Named("tourCategoryCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TourCategoryDTO toDtoTourCategoryCode(TourCategory tourCategory);

    @Named("placeCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    PlaceDTO toDtoPlaceCode(Place place);

    @Named("placeCategoryCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    PlaceCategoryDTO toDtoPlaceCategoryCode(PlaceCategory placeCategory);

    @Named("tourExtraCategoryCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TourExtraCategoryDTO toDtoTourExtraCategoryCode(TourExtraCategory tourExtraCategory);

    @Named("tourExtraCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TourExtraDTO toDtoTourExtraCode(TourExtra tourExtra);

    @Named("menuCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    MenuDTO toDtoMenuCode(Menu menu);

    @Named("webPageCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    WebPageDTO toDtoWebPageCode(WebPage webPage);

    @Named("tagCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TagDTO toDtoTagCode(Tag tag);

    @Named("tourStepCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TourStepDTO toDtoTourStepCode(TourStep tourStep);

    @Named("promotionCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    PromotionDTO toDtoPromotionCode(Promotion promotion);

    @Named("imageFileCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    ImageFileDTO toDtoImageFileCode(ImageFile imageFile);
}
