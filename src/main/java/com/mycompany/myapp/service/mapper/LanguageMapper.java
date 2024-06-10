package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Language;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.service.dto.LanguageDTO;
import com.mycompany.myapp.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Language} and its DTO {@link LanguageDTO}.
 */
@Mapper(componentModel = "spring")
public interface LanguageMapper extends EntityMapper<LanguageDTO, Language> {
    @Mapping(target = "createdBy", source = "createdBy", qualifiedByName = "userLogin")
    LanguageDTO toDto(Language s);

    @Named("userLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserDTO toDtoUserLogin(User user);
}
