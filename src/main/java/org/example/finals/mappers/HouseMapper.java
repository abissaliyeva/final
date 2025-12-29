package org.example.finals.mappers;

import org.example.finals.dto.house.HouseRequestDto;
import org.example.finals.dto.house.HouseResponseDto;
import org.example.finals.entities.House;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface HouseMapper {

    House toEntity(HouseRequestDto dto);

    @Mapping(expression = "java(house.getMembers() == null ? 0 : house.getMembers().size())",
            target = "memberCount")
    HouseResponseDto toResponse(House house);
}
