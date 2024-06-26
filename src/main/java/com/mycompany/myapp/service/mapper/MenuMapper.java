package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Destination;
import com.mycompany.myapp.domain.Menu;
import com.mycompany.myapp.domain.TourCategory;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.domain.WebPage;
import com.mycompany.myapp.service.dto.DestinationDTO;
import com.mycompany.myapp.service.dto.MenuDTO;
import com.mycompany.myapp.service.dto.TourCategoryDTO;
import com.mycompany.myapp.service.dto.UserDTO;
import com.mycompany.myapp.service.dto.WebPageDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Menu} and its DTO {@link MenuDTO}.
 */
@Mapper(componentModel = "spring")
public interface MenuMapper extends EntityMapper<MenuDTO, Menu> {
    @Mapping(target = "createdBy", source = "createdBy", qualifiedByName = "userLogin")
    @Mapping(target = "page", source = "page", qualifiedByName = "webPageCode")
    @Mapping(target = "parent", source = "parent", qualifiedByName = "menuCode")
    @Mapping(target = "tourCategory", source = "tourCategory", qualifiedByName = "tourCategoryCode")
    @Mapping(target = "destination", source = "destination", qualifiedByName = "destinationCode")
    MenuDTO toDto(Menu s);

    @Named("menuCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    MenuDTO toDtoMenuCode(Menu menu);

    @Named("userLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserDTO toDtoUserLogin(User user);

    @Named("webPageCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    WebPageDTO toDtoWebPageCode(WebPage webPage);

    @Named("tourCategoryCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TourCategoryDTO toDtoTourCategoryCode(TourCategory tourCategory);

    @Named("destinationCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    DestinationDTO toDtoDestinationCode(Destination destination);
}
