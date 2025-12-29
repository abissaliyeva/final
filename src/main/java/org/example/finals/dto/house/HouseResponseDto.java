package org.example.finals.dto.house;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseResponseDto {
    private int id;
    private String name;
    private String founder;
    private int points;
    private int memberCount;
}
