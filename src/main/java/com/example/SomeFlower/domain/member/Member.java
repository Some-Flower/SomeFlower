package com.example.SomeFlower.domain.member;

import com.example.SomeFlower.domain.member.dto.MemberDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity @Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="member")
public class Member{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String email;

    private String pwd;

    private String nickName;

    private String phoneNumber;

    private String profileImage;

    @Enumerated(value = EnumType.STRING) //저장될 때 int가 아닌 string 그자체로 저장되도록
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private MemberStatus status;

    public void update(MemberDto.UpdateDto updateDto) {
        this.nickName = updateDto.getNickName();
        this.phoneNumber = updateDto.getPhoneNumber();
        this.profileImage = updateDto.getProfileImage();
    }

    public void setStatus(MemberStatus status) { this.status = status; }

    public void setPwd(String pwd){
        this.pwd = pwd;
    }

    public void setRole(Role role) { this.role = role;}
}
