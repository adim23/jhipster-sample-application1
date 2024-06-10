package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Passenger;
import com.mycompany.myapp.service.dto.PassengerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Passenger} and its DTO {@link PassengerDTO}.
 */
@Mapper(componentModel = "spring")
public interface PassengerMapper extends EntityMapper<PassengerDTO, Passenger> {}
