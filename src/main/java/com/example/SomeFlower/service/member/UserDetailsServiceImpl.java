//package com.example.SomeFlower.service.member;
//
//import com.example.SomeFlower.domain.member.Member;
//import com.example.SomeFlower.domain.member.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final MemberRepository memberRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        log.info("PrincipalDetailService.loadUserByUsername");
//        log.info("LOGIN");
//        Member member = memberRepository.findByEmail(email).orElseThrow();
//
//        return new UserDetailsImpl(member);
//    }
//}
