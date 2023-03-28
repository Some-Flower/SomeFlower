package com.example.SomeFlower.domain.member.dto;

import com.example.SomeFlower.domain.member.Member;
import com.example.SomeFlower.domain.member.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MemberAdapter implements UserDetails {

    private Member member;

    public MemberAdapter(Member member){
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole().name();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return member.getPwd();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Member getMember(){
        return member;
    }
}
