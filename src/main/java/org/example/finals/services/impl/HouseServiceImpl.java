package org.example.finals.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.finals.dto.house.HouseRequestDto;
import org.example.finals.dto.house.HouseResponseDto;
import org.example.finals.mappers.HouseMapper;
import org.example.finals.repositories.HouseRepository;
import org.example.finals.services.HouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository repository;
    private final HouseMapper mapper;

    public HouseResponseDto create(HouseRequestDto dto) {
        return mapper.toResponse(repository.save(mapper.toEntity(dto)));
    }

    public List<HouseResponseDto> getAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }
}
