package com.example.SomeFlower.domain.userGroup.seller;

import com.example.SomeFlower.domain.flowerShop.FlowerShop;
import com.example.SomeFlower.domain.userGroup.Role;
import com.example.SomeFlower.domain.userGroup.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Builder @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name  = "seller")
public class Seller {

    @Id @GeneratedValue
    @Column(name = "seller_id")
    private Long id;
    private String email;
    private String pwd;
    private String name;
    private String phoneNumber;
    private String profileImage;

    @OneToMany(mappedBy = "seller",
            cascade = CascadeType.ALL,orphanRemoval = true)
    private List<FlowerShop> flowerShops = new ArrayList();

    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private Status status;

//    public void update(SellerDto.UpdateDto updateDto){
//        this.name = updateDto.get
//    }

    public static Seller createSeller(String email,String pwd,String name, String phoneNumber,
                                      String profileImage,Role role, Status status){
        return Seller.builder()
                .email(email)
                .pwd(pwd)
                .name(name)
                .phoneNumber(phoneNumber)
                .profileImage(profileImage)
                .role(role)
                .status(status)
                .build();
    }

    public void putFlowerShop(FlowerShop flowerShop){
        this.flowerShops.add(flowerShop);
    }

    public void setStatus(Status status){this.status = status; }
    public void setRole(Role role){this.role = role; }
    public void setPwd(String pwd) { this.pwd = pwd; }

}
