package com.example.SomeFlower.controller.member;

import com.example.SomeFlower.config.CurrentUser;
import com.example.SomeFlower.config.resTemplate.ResponseTemplate;
import com.example.SomeFlower.domain.member.Member;
import com.example.SomeFlower.domain.member.MemberStatus;
import com.example.SomeFlower.domain.member.dto.MemberDto;
import com.example.SomeFlower.service.member.MemberService;
import com.example.SomeFlower.util.AuthToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static com.example.SomeFlower.constant.ResponseTemplateStatus.SUCCESS;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    /**
     * 일반 회원가입
     */
    @PostMapping("/join")
    public ResponseTemplate<Void> createMember(@RequestBody MemberDto joinDto) throws Exception {
        memberService.join(joinDto);
        return ResponseTemplate.of(SUCCESS);
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<ResponseTemplate<String>> loginMember(@RequestBody MemberDto.LoginDto loginDto) throws ResponseStatusException{
        AuthToken token = memberService.login(loginDto);
        return ResponseEntity.ok()
                .headers(HttpHeaders.readOnlyHttpHeaders(token.asHeaders()))
                .body(ResponseTemplate.valueOf("login success"));
    }

    /**
     * 유저 정보 조회
     */
    @GetMapping("/getInfo")
    public ResponseTemplate<Optional<Member>> getMemberInfo(@CurrentUser Member member) throws Exception{
        return ResponseTemplate.valueOf(Optional.ofNullable(member));
    }

    /**
     * 유저 정보 수정
     */
    @PutMapping("/update")
    public ResponseTemplate<MemberDto> updateMember(@RequestBody MemberDto updateDto,
                                               @CurrentUser MemberDto.UpdateDto currentUser) throws Exception{
        MemberDto updateMember = memberService.update(updateDto.getId(), currentUser);
        return ResponseTemplate.valueOf(updateMember);
    }

    /**
     * 유저 삭제 -> 삭제 상태로 변경
     */
    @DeleteMapping("/delete")
    public ResponseTemplate<Void> withDrawMember(@AuthenticationPrincipal MemberDto memberDto) throws  ResponseStatusException{
        memberService.changeMemberStatus(memberDto.getId(), MemberStatus.DELETED);
        return ResponseTemplate.of(SUCCESS);
    }



}
