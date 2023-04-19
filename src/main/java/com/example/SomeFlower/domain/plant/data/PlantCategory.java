package com.example.SomeFlower.domain.plant.data;


import jakarta.persistence.*;
import lombok.Getter;

//manytomany 연결
@Entity @Getter
public class PlantCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;


}
