package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Destination;
import com.mycompany.myapp.domain.Driver;
import com.mycompany.myapp.domain.ImageFile;
import com.mycompany.myapp.domain.Place;
import com.mycompany.myapp.domain.PlaceCategory;
import com.mycompany.myapp.domain.Tour;
import com.mycompany.myapp.domain.TourCategory;
import com.mycompany.myapp.domain.TourExtra;
import com.mycompany.myapp.domain.TourExtraCategory;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.domain.Vehicle;
import com.mycompany.myapp.service.dto.DestinationDTO;
import com.mycompany.myapp.service.dto.DriverDTO;
import com.mycompany.myapp.service.dto.ImageFileDTO;
import com.mycompany.myapp.service.dto.PlaceCategoryDTO;
import com.mycompany.myapp.service.dto.PlaceDTO;
import com.mycompany.myapp.service.dto.TourCategoryDTO;
import com.mycompany.myapp.service.dto.TourDTO;
import com.mycompany.myapp.service.dto.TourExtraCategoryDTO;
import com.mycompany.myapp.service.dto.TourExtraDTO;
import com.mycompany.myapp.service.dto.UserDTO;
import com.mycompany.myapp.service.dto.VehicleDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ImageFile} and its DTO {@link ImageFileDTO}.
 */
@Mapper(componentModel = "spring")
public interface ImageFileMapper extends EntityMapper<ImageFileDTO, ImageFile> {
    @Mapping(target = "createdBy", source = "createdBy", qualifiedByName = "userLogin")
    @Mapping(target = "destination", source = "destination", qualifiedByName = "destinationCode")
    @Mapping(target = "tour", source = "tour", qualifiedByName = "tourCode")
    @Mapping(target = "tourCategory", source = "tourCategory", qualifiedByName = "tourCategoryCode")
    @Mapping(target = "place", source = "place", qualifiedByName = "placeCode")
    @Mapping(target = "placeCategory", source = "placeCategory", qualifiedByName = "placeCategoryCode")
    @Mapping(target = "tourExtraCategory", source = "tourExtraCategory", qualifiedByName = "tourExtraCategoryCode")
    @Mapping(target = "tourExtra", source = "tourExtra", qualifiedByName = "tourExtraCode")
    @Mapping(target = "vehicle", source = "vehicle", qualifiedByName = "vehiclePlate")
    @Mapping(target = "driver", source = "driver", qualifiedByName = "driverName")
    ImageFileDTO toDto(ImageFile s);

    @Named("userLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserDTO toDtoUserLogin(User user);

    @Named("destinationCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    DestinationDTO toDtoDestinationCode(Destination destination);

    @Named("tourCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TourDTO toDtoTourCode(Tour tour);

    @Named("tourCategoryCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TourCategoryDTO toDtoTourCategoryCode(TourCategory tourCategory);

    @Named("placeCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    PlaceDTO toDtoPlaceCode(Place place);

    @Named("placeCategoryCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    PlaceCategoryDTO toDtoPlaceCategoryCode(PlaceCategory placeCategory);

    @Named("tourExtraCategoryCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TourExtraCategoryDTO toDtoTourExtraCategoryCode(TourExtraCategory tourExtraCategory);

    @Named("tourExtraCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "code", source = "code")
    TourExtraDTO toDtoTourExtraCode(TourExtra tourExtra);

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
