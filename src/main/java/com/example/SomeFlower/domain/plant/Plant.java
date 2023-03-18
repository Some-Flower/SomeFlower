package com.example.SomeFlower.domain.plant;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity @Builder @Getter
//상소고간계 전략
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//구분해주는 역할 카테고리 별로
@NoArgsConstructor
@AllArgsConstructor
public class Plant {

    @Id @GeneratedValue
    @Column(name = "plant_id")
    private Long id;

    private String name;
    private String description;
    private String color;
    private int price;
    private String image;
    private String wateringCycle;
    private int stockQuantity;

    @OneToMany(mappedBy = "plant")
    private List<CategoryPlant> categories = new ArrayList<>();



}
