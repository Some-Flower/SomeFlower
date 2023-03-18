package com.example.SomeFlower.domain.plant;

import com.example.SomeFlower.domain.plant.dto.*;

public class PlantAdapter {

    public static Plant toEntity(PlantDto plantDto){
        return Plant.builder()
                .id(plantDto.getId())
                .name(plantDto.getName())
                .description(plantDto.getDescription())
                .color(plantDto.getColor())
                .price(plantDto.getPrice())
                .image(plantDto.getImage())
                .wateringCycle((plantDto.getWateringCycle()))
                .stockQuantity(plantDto.getStockQuantity())
                .build();
    }

    public static Flower toEntity(FlowerDto flowerDto){
        return Flower.builder()
                .id(flowerDto.getFlowerId())
                .flowerLanguage(flowerDto.getFlowerLanguage())
                .plant(toEntity(flowerDto.getSuperPlantDto()))
                .build();
    }

    public static Fleshy toEntity(FleshyDto fleshyDto){
        return Fleshy.builder()
                .id(fleshyDto.getFleshyId())
                .width(fleshyDto.getWidth())
                .height(fleshyDto.getHeight())
                .plant(toEntity(fleshyDto.getSuperPlantDto()))
                .build();
    }

    public static Pot toEntity(PotDto potDto){
        return Pot.builder()
                .id(potDto.getPotId())
                .width(potDto.getWidth())
                .height(potDto.getHeight())
                .plant(toEntity(potDto.getSuperPlantDto()))
                .build();
    }

    public static Bulb toEntity(BulbDto bulbDto){
        return Bulb.builder()
                .id(bulbDto.getBulbId())
                .flowerLanguage(bulbDto.getFlowerLanguage())
                .weight(bulbDto.getWeight())
                .plant(toEntity(bulbDto.getSuperPlantDto()))
                .build();
    }

    public static PlantDto toDto(Plant plant) {
        return PlantDto.builder()
                .id(plant.getId())
                .name(plant.getName())
                .description(plant.getDescription())
                .color(plant.getColor())
                .price(plant.getPrice())
                .image(plant.getImage())
                .wateringCycle((plant.getWateringCycle()))
                .stockQuantity(plant.getStockQuantity())
                .build();
    }

    public static PlantDto toDto(Flower flower) {
        return FlowerDto.builder()
                .id(flower.getId())
                .flowerLanguage(flower.getFlowerLanguage())
                .build();
    }

    public static PlantDto toDto(Pot pot) {
        return PotDto.builder()
                .id(pot.getId())
                .width(pot.getWidth())
                .height(pot.getHeight())
                .build();
    }

    public static PlantDto toDto(Fleshy fleshy) {
        return PotDto.builder()
                .id(fleshy.getId())
                .width(fleshy.getWidth())
                .height(fleshy.getHeight())
                .build();
    }

    public static PlantDto toDto(Bulb bulb) {
        return BulbDto.builder()
                .id(bulb.getId())
                .flowerLanguage(bulb.getFlowerLanguage())
                .weight(bulb.getWeight())
                .build();
    }


}
