package com.mycompany.myapp.service.mapper;

import static com.mycompany.myapp.domain.TourCategoryAsserts.*;
import static com.mycompany.myapp.domain.TourCategoryTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TourCategoryMapperTest {

    private TourCategoryMapper tourCategoryMapper;

    @BeforeEach
    void setUp() {
        tourCategoryMapper = new TourCategoryMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getTourCategorySample1();
        var actual = tourCategoryMapper.toEntity(tourCategoryMapper.toDto(expected));
        assertTourCategoryAllPropertiesEquals(expected, actual);
    }
}
