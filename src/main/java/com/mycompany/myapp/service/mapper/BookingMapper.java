package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Booking;
import com.mycompany.myapp.domain.Passenger;
import com.mycompany.myapp.domain.TourSchedule;
import com.mycompany.myapp.service.dto.BookingDTO;
import com.mycompany.myapp.service.dto.PassengerDTO;
import com.mycompany.myapp.service.dto.TourScheduleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Booking} and its DTO {@link BookingDTO}.
 */
@Mapper(componentModel = "spring")
public interface BookingMapper extends EntityMapper<BookingDTO, Booking> {
    @Mapping(target = "schedule", source = "schedule", qualifiedByName = "tourScheduleCode")
    @Mapping(target = "passenger", source = "passenger", qualifiedByName = "passengerName")
    BookingDTO toDto(Booking s);

    @Named("tourScheduleCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TourScheduleDTO toDtoTourScheduleCode(TourSchedule tourSchedule);

    @Named("passengerName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    PassengerDTO toDtoPassengerName(Passenger passenger);
}
