package com.example.SomeFlower.domain.plant;


import jakarta.persistence.*;
import lombok.Getter;

//manytomany 연결
@Entity @Getter
public class CategoryPlant {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;


}
