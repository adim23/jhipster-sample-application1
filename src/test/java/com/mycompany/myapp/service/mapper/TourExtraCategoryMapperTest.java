package com.mycompany.myapp.service.mapper;

import static com.mycompany.myapp.domain.TourExtraCategoryAsserts.*;
import static com.mycompany.myapp.domain.TourExtraCategoryTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TourExtraCategoryMapperTest {

    private TourExtraCategoryMapper tourExtraCategoryMapper;

    @BeforeEach
    void setUp() {
        tourExtraCategoryMapper = new TourExtraCategoryMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getTourExtraCategorySample1();
        var actual = tourExtraCategoryMapper.toEntity(tourExtraCategoryMapper.toDto(expected));
        assertTourExtraCategoryAllPropertiesEquals(expected, actual);
    }
}
