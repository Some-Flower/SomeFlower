package com.example.SomeFlower.domain.plant.data;

import com.example.SomeFlower.domain.userGroup.seller.Seller;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity @Builder @Getter
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

    /**
     * 판매자가 상품판매를 등록하면 (Repository 를 통해 DB에 저장하면)
     * CascadeType.ALL 옵션을 통해 회원 판매목록과 아이템카테고리에도 DB call
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL)
    private List<PlantCategory> plantCategories;



}
