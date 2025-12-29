package org.example.finals.mappers;

import org.example.finals.dto.user.UserRequestDto;
import org.example.finals.dto.user.UserResponseDto;
import org.example.finals.entities.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "house", ignore = true)
    User toEntity(UserRequestDto dto);

    @Mapping(source = "house.name", target = "houseName")
    UserResponseDto toResponse(User user);
}
