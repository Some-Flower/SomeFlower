package com.example.SomeFlower.domain.order;

import com.example.SomeFlower.domain.plant.data.Plant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderPlant {

    @Id @GeneratedValue
    @Column(name = "order_plant_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "plant_id")
    private Plant plant;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    private int orderPrice;
    private int quantity;

    public Order getOrder() {return order;}


}
