package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Language;
import com.mycompany.myapp.domain.Prompt;
import com.mycompany.myapp.service.dto.LanguageDTO;
import com.mycompany.myapp.service.dto.PromptDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Prompt} and its DTO {@link PromptDTO}.
 */
@Mapper(componentModel = "spring")
public interface PromptMapper extends EntityMapper<PromptDTO, Prompt> {
    @Mapping(target = "language", source = "language", qualifiedByName = "languageCode")
    PromptDTO toDto(Prompt s);

    @Named("languageCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    LanguageDTO toDtoLanguageCode(Language language);
}
