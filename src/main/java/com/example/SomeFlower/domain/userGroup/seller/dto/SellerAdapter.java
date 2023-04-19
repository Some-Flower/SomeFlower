package com.example.SomeFlower.domain.userGroup.seller.dto;

import com.example.SomeFlower.domain.userGroup.seller.Seller;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
@AllArgsConstructor
public class SellerAdapter implements UserDetails {

    private Seller seller;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_" + seller.getRole().name();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return seller.getPwd();
    }

    @Override
    public String getUsername() {
        return seller.getEmail();
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

    public Seller getSeller(){
        return seller;
    }
}
