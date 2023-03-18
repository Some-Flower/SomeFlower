package com.example.SomeFlower.domain.flowerShop;

import com.example.SomeFlower.domain.flowerShop.Address;
import jakarta.persistence.*;
import lombok.Getter;

@Entity @Getter
public class FlowerShop {

    @Id @GeneratedValue
    @Column(name ="flowershop_id")
    private Long id;

    private String shopName;

    @Embedded
    private Address address;

}
