package com.mycompany.myapp.service.mapper;

import static com.mycompany.myapp.domain.TourStepAsserts.*;
import static com.mycompany.myapp.domain.TourStepTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TourStepMapperTest {

    private TourStepMapper tourStepMapper;

    @BeforeEach
    void setUp() {
        tourStepMapper = new TourStepMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getTourStepSample1();
        var actual = tourStepMapper.toEntity(tourStepMapper.toDto(expected));
        assertTourStepAllPropertiesEquals(expected, actual);
    }
}
