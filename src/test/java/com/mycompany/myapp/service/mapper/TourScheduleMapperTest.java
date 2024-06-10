package com.mycompany.myapp.service.mapper;

import static com.mycompany.myapp.domain.TourScheduleAsserts.*;
import static com.mycompany.myapp.domain.TourScheduleTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TourScheduleMapperTest {

    private TourScheduleMapper tourScheduleMapper;

    @BeforeEach
    void setUp() {
        tourScheduleMapper = new TourScheduleMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getTourScheduleSample1();
        var actual = tourScheduleMapper.toEntity(tourScheduleMapper.toDto(expected));
        assertTourScheduleAllPropertiesEquals(expected, actual);
    }
}
