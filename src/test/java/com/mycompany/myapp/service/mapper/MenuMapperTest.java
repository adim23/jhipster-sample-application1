package com.mycompany.myapp.service.mapper;

import static com.mycompany.myapp.domain.MenuAsserts.*;
import static com.mycompany.myapp.domain.MenuTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenuMapperTest {

    private MenuMapper menuMapper;

    @BeforeEach
    void setUp() {
        menuMapper = new MenuMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getMenuSample1();
        var actual = menuMapper.toEntity(menuMapper.toDto(expected));
        assertMenuAllPropertiesEquals(expected, actual);
    }
}
