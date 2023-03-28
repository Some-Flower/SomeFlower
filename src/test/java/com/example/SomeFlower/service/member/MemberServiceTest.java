//package com.example.SomeFlower.service.member;
//
//import com.example.SomeFlower.domain.member.Member;
//import com.example.SomeFlower.domain.member.Role;
//import com.example.SomeFlower.domain.member.dto.MemberDto;
//import com.example.SomeFlower.domain.member.repository.MemberRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.Collections;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//
//class MemberServiceTest {
//
//    @Mock MemberRepository memberRepository;
//    @Mock PasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    private MemberService memberService;
//
//    @Test
//    @DisplayName("회원가입 성공")
//    void 회원가입() throws Exception {
//        //given
//        MemberDto memberJoinDto = MemberDto.builder()
//                .email("test1234@naver.com")
//                .pwd("sdkjflksdjg")
//                .nickName("dd")
//                .profileImage("qwkj")
//                .role(Role.USER)
//                .build();
//        // 테스트 과정에서 userrepository의 save메서드를 호출하면 즉시 memberjoindto의 이메일을 반환하도록 한다.
//        // 실제로 저장은 안하고 mock이기 때문에??
//        when(memberRepository.save(any(Member.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        //when
//        Long result = memberService.join(memberJoinDto);
//        //then
//        assertEquals(memberJoinDto.getId(),result);
//
//    }
////
////    @Test
////    @DisplayName("중복된 이메일로 가입 시도 시 회원가입 실패")
////    public void 이메일_중복() throws Exception {
////        //given
////        MemberDto memberJoinDto = MemberDto.builder()
////                .email("test1234@naver.com")
////                .pwd("sdkjflksdjg")
////                .nickName("dd")
////                .profileImage("qwkj")
////                .build();
////
////        Member user = mock(Member.class);
////        //어떠한 내용의 이메일 조회 요청이 와도 중복된 에미일이 있다고 결과를 반환해준다.
////        when(memberRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
////
////        assertThrows(ResponseStatusException.class,() -> {memberService.join(memberJoinDto);});
////    }
//
//    private Long generateMember() throws Exception{
//        MemberDto memberDto = MemberDto.builder()
//                .email("test@naver.com")
//                .pwd("sdkjflksdjg")
//                .nickName("dsf")
//                .profileImage("sdvxc")
//                .role(Role.USER)
//                .build();
//        return memberService.join(memberDto);
//    }
//
////    @Test
////    void 회원정보_수정() throws Exception{
////        MemberDto.MemberUpdateDto memberDto = MemberDto.MemberUpdateDto.builder()
////                .nickName("d11sf")
////                .profileImage("sd11vxc")
////                .build();
////
////        Member member = mock(Member.class);
////        when(memberRepository.findById(anyLong())).thenReturn(Optional.of(member));
////        when(memberDetails.getUserId()).thenReturn(1L);
////
////        memberService.updateMember(memberDetails, memberDto);
////
////    }
//}