package com.example.SomeFlower.service.member;

import com.example.SomeFlower.domain.member.Member;
import com.example.SomeFlower.domain.member.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Builder
@Configuration
//시큐리티 설정
public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String email;
    private String pwd;
    private Set<Role> role;

    public UserDetailsImpl(Long id, String email, Set<Role> role, String pwd){
        this.id = id;
        this.email = email;
        this.pwd = pwd;
        this.role = role;
    }

    public UserDetailsImpl(Member member){
        this.id = member.getId();
        this.email = member.getEmail();
        this.pwd = member.getPwd();
        this.role = member.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(this.role.toString()));
        return list;
    }

    @Override
    public String getPassword() {
        return this.pwd;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public Long getUserId(){return this.id;}

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
