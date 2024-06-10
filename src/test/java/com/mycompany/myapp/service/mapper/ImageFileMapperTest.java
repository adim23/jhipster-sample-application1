package com.mycompany.myapp.service.mapper;

import static com.mycompany.myapp.domain.ImageFileAsserts.*;
import static com.mycompany.myapp.domain.ImageFileTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ImageFileMapperTest {

    private ImageFileMapper imageFileMapper;

    @BeforeEach
    void setUp() {
        imageFileMapper = new ImageFileMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getImageFileSample1();
        var actual = imageFileMapper.toEntity(imageFileMapper.toDto(expected));
        assertImageFileAllPropertiesEquals(expected, actual);
    }
}
