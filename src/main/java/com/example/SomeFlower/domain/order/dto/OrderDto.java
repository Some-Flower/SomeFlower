package com.example.SomeFlower.domain.order.dto;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private List<OrderPlantDto> orderPlantDtoList;


}
