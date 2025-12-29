package org.example.finals.controllers;

import lombok.RequiredArgsConstructor;
import org.example.finals.dto.house.HouseRequestDto;
import org.example.finals.dto.house.HouseResponseDto;
import org.example.finals.services.HouseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/houses")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public HouseResponseDto create(@RequestBody HouseRequestDto dto) {
        return houseService.create(dto);
    }

    @GetMapping
    public List<HouseResponseDto> getAll() {
        return houseService.getAll();
    }
}
