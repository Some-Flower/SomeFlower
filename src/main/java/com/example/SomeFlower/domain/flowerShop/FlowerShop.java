package com.example.SomeFlower.domain.flowerShop;

import com.example.SomeFlower.domain.userGroup.seller.Seller;
import jakarta.persistence.*;
import lombok.Getter;

@Entity @Getter
public class FlowerShop {

    @Id @GeneratedValue
    @Column(name ="flowershop_id")
    private Long id;

    private String shopName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @Embedded
    private ShopAddress shopAddress;



}
