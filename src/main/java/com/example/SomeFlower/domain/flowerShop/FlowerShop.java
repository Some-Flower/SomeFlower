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

    @Id @GeneratedValue
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

    public static FlowerShop createFlowerShop(String shopName,String description,String shopImage,String shopLink,String businessHours,
                                              String businessDates,String shopNumber,Seller seller,Address address){
        return FlowerShop.builder()
                .shopName(shopName)
                .description(description)
                .shopImage(shopImage)
                .shopLink(shopLink)
                .businessHours(businessHours)
                .businessDates(businessDates)
                .shopNumber(shopNumber)
                .seller(seller)
                .address(address)
                .build();
    }

}
