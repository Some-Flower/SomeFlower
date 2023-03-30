package com.example.SomeFlower.domain.plant.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bulb{

    @Id @GeneratedValue
    @Column(name = "bulb_id")
    private Long id;

    private String flowerLanguage;
    private int weight;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_id")
    private Plant plant;
}
