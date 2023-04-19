package com.example.SomeFlower.domain.userGroup.seller;

import com.example.SomeFlower.domain.flowerShop.FlowerShop;
import com.example.SomeFlower.domain.userGroup.Role;
import com.example.SomeFlower.domain.userGroup.Status;
import com.example.SomeFlower.domain.userGroup.seller.dto.SellerUpdateDto;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name  = "seller")
public class Seller {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Long id;
    private String email;
    private String pwd;
    private String name;
    private String phoneNumber;
    private String profileImage;

    @OneToMany(mappedBy = "seller",
            cascade = CascadeType.ALL)
    private List<FlowerShop> flowerShops;

    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private Status status;

    public void setStatus(Status status){this.status = status; }
    public void setRole(Role role){this.role = role; }
    public void setPwd(String pwd) { this.pwd = pwd; }

    public void update(SellerUpdateDto sellerUpdateDto) {
        this.name = sellerUpdateDto.getName();
        this.phoneNumber = sellerUpdateDto.getPhoneNumber();
        this.profileImage = sellerUpdateDto.getProfileImage();
    }
}
