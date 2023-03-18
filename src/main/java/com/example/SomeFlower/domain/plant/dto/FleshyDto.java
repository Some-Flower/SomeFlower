package com.example.SomeFlower.domain.plant.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter @SuperBuilder
public class FleshyDto extends PlantDto {

    private Long fleshyId;

    private int width;
    private int height;

}
