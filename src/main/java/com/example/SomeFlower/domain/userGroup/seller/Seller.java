package com.example.SomeFlower.domain.userGroup.seller;

import com.example.SomeFlower.domain.flowerShop.FlowerShop;
import com.example.SomeFlower.domain.userGroup.member.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Seller {

    @Id @GeneratedValue
    @Column(name = "seller_id")
    private Long id;

    private String email;

    private String pwd;

    private String name;

    private String phoneNumber;

    private String profileImage;

    @OneToMany(mappedBy = "seller")
    private List<FlowerShop> flowerShopList;

    @Enumerated(value = EnumType.STRING)
    private Role role;

}
