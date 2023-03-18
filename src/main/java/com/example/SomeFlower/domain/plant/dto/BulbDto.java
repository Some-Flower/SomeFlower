package com.example.SomeFlower.domain.plant.dto;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter @SuperBuilder
public class BulbDto extends PlantDto{

    private Long bulbId;

    private String flowerLanguage;
    private int weight;

}
