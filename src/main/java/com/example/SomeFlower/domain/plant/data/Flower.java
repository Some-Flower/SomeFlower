package com.example.SomeFlower.domain.plant.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("flower")
public class Flower{

    @Id @GeneratedValue
    @Column(name = "flower_id")
    private Long id;

    private String flowerLanguage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_id")
    private Plant plant;
}
