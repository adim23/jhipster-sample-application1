package com.mycompany.myapp.service.mapper;

import static com.mycompany.myapp.domain.WebPageAsserts.*;
import static com.mycompany.myapp.domain.WebPageTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WebPageMapperTest {

    private WebPageMapper webPageMapper;

    @BeforeEach
    void setUp() {
        webPageMapper = new WebPageMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getWebPageSample1();
        var actual = webPageMapper.toEntity(webPageMapper.toDto(expected));
        assertWebPageAllPropertiesEquals(expected, actual);
    }
}
