package com.example.SomeFlower.domain.userGroup.member;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class MemberAddress {

    private String city;
    private String street;
    private String zipcode;
    private String details;

}
