package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Driver;
import com.mycompany.myapp.domain.Tour;
import com.mycompany.myapp.domain.TourSchedule;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.domain.Vehicle;
import com.mycompany.myapp.service.dto.DriverDTO;
import com.mycompany.myapp.service.dto.TourDTO;
import com.mycompany.myapp.service.dto.TourScheduleDTO;
import com.mycompany.myapp.service.dto.UserDTO;
import com.mycompany.myapp.service.dto.VehicleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TourSchedule} and its DTO {@link TourScheduleDTO}.
 */
@Mapper(componentModel = "spring")
public interface TourScheduleMapper extends EntityMapper<TourScheduleDTO, TourSchedule> {
    @Mapping(target = "createdBy", source = "createdBy", qualifiedByName = "userLogin")
    @Mapping(target = "tour", source = "tour", qualifiedByName = "tourCode")
    @Mapping(target = "vehicle", source = "vehicle", qualifiedByName = "vehiclePlate")
    @Mapping(target = "driver", source = "driver", qualifiedByName = "driverName")
    TourScheduleDTO toDto(TourSchedule s);

    @Named("userLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserDTO toDtoUserLogin(User user);

    @Named("tourCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TourDTO toDtoTourCode(Tour tour);

    @Named("vehiclePlate")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "plate", source = "plate")
    VehicleDTO toDtoVehiclePlate(Vehicle vehicle);

    @Named("driverName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    DriverDTO toDtoDriverName(Driver driver);
}
