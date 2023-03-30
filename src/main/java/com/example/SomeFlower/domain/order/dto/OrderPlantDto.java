package com.example.SomeFlower.domain.order.dto;

import com.example.SomeFlower.domain.order.OrderPlant;
import com.example.SomeFlower.domain.plant.dto.PlantDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPlantDto {

    private int orderPrice;
    private int quantity;
    private PlantDto plantDto;

    public OrderPlant toEntity(){
        return OrderPlant.builder()
                .orderPrice(this.orderPrice)
                .quantity(this.quantity)
                .plant(this.plantDto.toEntity())
                .build();
    }
}
