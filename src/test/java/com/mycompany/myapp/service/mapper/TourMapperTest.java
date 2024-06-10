package com.mycompany.myapp.service.mapper;

import static com.mycompany.myapp.domain.TourAsserts.*;
import static com.mycompany.myapp.domain.TourTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TourMapperTest {

    private TourMapper tourMapper;

    @BeforeEach
    void setUp() {
        tourMapper = new TourMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getTourSample1();
        var actual = tourMapper.toEntity(tourMapper.toDto(expected));
        assertTourAllPropertiesEquals(expected, actual);
    }
}
