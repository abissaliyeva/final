package com.example.finals;

import com.example.finals.DTO.house.*;
import com.example.finals.services.impl.HouseServiceImpl;
import com.example.finals.repositories.HouseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class HouseServiceImplTest {

    @Autowired
    private HouseServiceImpl houseService;

    @Autowired
    private HouseRepository houseRepository;

    @Test
    void createHouseTest() {
        HouseRequestDto dto = HouseRequestDto.builder()
                .name("Gryffindor")
                .build();

        HouseResponseDto response = houseService.create(dto);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getId());
        Assertions.assertEquals("Gryffindor", response.getName());
    }

    @Test
    void getAllHousesTest() {
        List<HouseResponseDto> houses = houseService.getAll();

        Assertions.assertNotNull(houses);
        Assertions.assertNotEquals(0, houses.size());

        for (HouseResponseDto dto : houses) {
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getName());
        }
    }
}
