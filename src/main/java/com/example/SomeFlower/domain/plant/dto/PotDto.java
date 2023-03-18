package com.example.SomeFlower.domain.plant.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder @Getter
public class PotDto extends PlantDto{

    private Long potId;

    private int width;
    private int height;

}
