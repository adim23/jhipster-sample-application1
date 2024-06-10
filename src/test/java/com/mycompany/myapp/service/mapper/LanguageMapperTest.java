package com.mycompany.myapp.service.mapper;

import static com.mycompany.myapp.domain.LanguageAsserts.*;
import static com.mycompany.myapp.domain.LanguageTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LanguageMapperTest {

    private LanguageMapper languageMapper;

    @BeforeEach
    void setUp() {
        languageMapper = new LanguageMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getLanguageSample1();
        var actual = languageMapper.toEntity(languageMapper.toDto(expected));
        assertLanguageAllPropertiesEquals(expected, actual);
    }
}
