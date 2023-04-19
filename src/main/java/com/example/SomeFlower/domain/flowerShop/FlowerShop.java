package com.example.SomeFlower.domain.flowerShop;

import com.example.SomeFlower.domain.userGroup.Address;
import com.example.SomeFlower.domain.userGroup.seller.Seller;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity @Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="flowershop")
public class FlowerShop {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="flowershop_id")
    private Long id;
    private String shopName;
    private String description;
    private String shopImage;
    private String shopLink;
    private String businessHours;
    private String businessDates;
    private String shopNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    private Address address;

    public void update(FlowerShopUpdateDto updateDto) {
        this.shopName = updateDto.getShopImage();
        this.description = updateDto.getDescription();
        this.shopImage = updateDto.getShopImage();
        this.shopLink = updateDto.getShopLink();
        this.businessHours = updateDto.getBusinessHours();
        this.businessDates = updateDto.getBusinessDates();
        this.shopNumber = updateDto.getShopNumber();
    }
}
