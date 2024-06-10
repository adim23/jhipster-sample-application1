package com.mycompany.myapp.service.mapper;

import static com.mycompany.myapp.domain.DestinationAsserts.*;
import static com.mycompany.myapp.domain.DestinationTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DestinationMapperTest {

    private DestinationMapper destinationMapper;

    @BeforeEach
    void setUp() {
        destinationMapper = new DestinationMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getDestinationSample1();
        var actual = destinationMapper.toEntity(destinationMapper.toDto(expected));
        assertDestinationAllPropertiesEquals(expected, actual);
    }
}
