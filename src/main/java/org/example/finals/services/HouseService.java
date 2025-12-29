package org.example.finals.services;

import org.example.finals.dto.house.HouseRequestDto;
import org.example.finals.dto.house.HouseResponseDto;

import java.util.List;

public interface HouseService {
    HouseResponseDto create(HouseRequestDto dto);
    List<HouseResponseDto> getAll();
}
