package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Language;
import com.mycompany.myapp.domain.TourContent;
import com.mycompany.myapp.service.dto.LanguageDTO;
import com.mycompany.myapp.service.dto.TourContentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TourContent} and its DTO {@link TourContentDTO}.
 */
@Mapper(componentModel = "spring")
public interface TourContentMapper extends EntityMapper<TourContentDTO, TourContent> {
    @Mapping(target = "language", source = "language", qualifiedByName = "languageCode")
    TourContentDTO toDto(TourContent s);

    @Named("languageCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    LanguageDTO toDtoLanguageCode(Language language);
}
