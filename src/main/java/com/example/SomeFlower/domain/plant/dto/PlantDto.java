package com.example.SomeFlower.domain.plant.dto;

import com.example.SomeFlower.domain.plant.data.Plant;
import com.example.SomeFlower.domain.plant.data.PlantCategory;
import com.example.SomeFlower.domain.userGroup.seller.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

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
    private PlantDto superPlantDto;
    private Seller seller;

    private List<PlantCategory> plantCategories;

    public Plant toEntity(){
        return Plant.builder()
                .id(this.id)
                .name(this.name)
                .description(this.description)
                .color(this.color)
                .image(this.image)
                .wateringCycle(this.wateringCycle)
                .price(this.price)
                .stockQuantity(this.stockQuantity)
                .plantCategories(this.plantCategories)
                .seller(this.seller)
                .build();
    }

}
