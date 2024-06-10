package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Destination;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.service.dto.DestinationDTO;
import com.mycompany.myapp.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Destination} and its DTO {@link DestinationDTO}.
 */
@Mapper(componentModel = "spring")
public interface DestinationMapper extends EntityMapper<DestinationDTO, Destination> {
    @Mapping(target = "createdBy", source = "createdBy", qualifiedByName = "userLogin")
    DestinationDTO toDto(Destination s);

    @Named("userLogin")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    UserDTO toDtoUserLogin(User user);
}
