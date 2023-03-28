//package com.example.SomeFlower.controller;
//
//import com.example.SomeFlower.domain.member.dto.MemberDto;
//import com.example.SomeFlower.domain.member.repository.MemberRepository;
//import com.example.SomeFlower.service.member.MemberService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class MemberControllerTest {
//
//    private MemberRepository memberRepository;
//    private MemberService memberService;
//
//    private Long generateMember() throws Exception{
//        MemberDto memberDto = MemberDto.builder()
//                .id(1L)
//                .email("t")
//                .pwd("tt")
//                .nickName("ttt")
//                .phoneNumber("tttt")
//                .profileImage("tttttt")
//                .build();
//        return memberService.join(memberDto);
//    }
//
//    @Test
//    void 회원정보수정() throws Exception{
//
//    }
//}
