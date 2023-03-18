package com.example.SomeFlower.domain.plant.dto;

import com.example.SomeFlower.domain.plant.PlantType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder @Getter
@AllArgsConstructor
public class PlantDto {

    private Long id;
    private String name;
    private String description;
    private String color;
    private String image;
    private String wateringCycle;
    private int price;
    private int stockQuantity;
    private PlantType plantType;

    private PlantDto superPlantDto;

}
