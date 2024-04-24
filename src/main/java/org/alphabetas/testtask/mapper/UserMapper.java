package org.alphabetas.testtask.mapper;

import org.alphabetas.testtask.model.User;
import org.alphabetas.testtask.model.UserDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserDto dto, @MappingTarget User entity);
}
