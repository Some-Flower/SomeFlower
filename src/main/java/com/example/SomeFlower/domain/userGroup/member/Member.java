package com.example.SomeFlower.domain.userGroup.member;

import com.example.SomeFlower.domain.userGroup.Address;
import com.example.SomeFlower.domain.userGroup.Role;
import com.example.SomeFlower.domain.userGroup.Status;
import com.example.SomeFlower.domain.userGroup.member.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="member")
public class Member{

    @Id @GeneratedValue
    private Long id;
    private String email;
    private String pwd;
    private String nickName;
    private String phoneNumber;
    private String profileImage;
    private Address address;

    @Enumerated(value = EnumType.STRING) //저장될 때 int가 아닌 string 그자체로 저장되도록
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    public void update(MemberDto.UpdateDto updateDto) {
        this.nickName = updateDto.getNickName();
        this.phoneNumber = updateDto.getPhoneNumber();
        this.profileImage = updateDto.getProfileImage();
        this.address = updateDto.getAddress();
    }

    public void setStatus(Status status) { this.status = status; }

    public void setPwd(String pwd){
        this.pwd = pwd;
    }

}
