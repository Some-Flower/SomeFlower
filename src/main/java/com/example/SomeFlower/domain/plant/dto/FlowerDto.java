package com.example.SomeFlower.domain.plant.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder @Getter
public class FlowerDto extends PlantDto {

    private Long flowerId;

    private String flowerLanguage;

}
