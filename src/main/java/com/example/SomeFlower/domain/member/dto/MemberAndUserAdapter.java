package com.example.SomeFlower.domain.member.dto;

import com.example.SomeFlower.domain.member.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class MemberAndUserAdapter extends User {

    private final MemberDto memberDto;

    private MemberAndUserAdapter(MemberDto memberDto) {
        super(memberDto.getEmail(), memberDto.getPwd(), authorities(memberDto.getRole()));
        this.memberDto = memberDto;
    }

    public static MemberAndUserAdapter from(MemberDto memberDto) {
        return new MemberAndUserAdapter(memberDto);
    }

    private static Collection<? extends GrantedAuthority> authorities(Set<Role> role) {
        return role.stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.name()))
                .collect(Collectors.toSet());
    }

    public MemberDto getMemberDto() { return memberDto; }
}
