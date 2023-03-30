package com.example.SomeFlower.controller.member;

import com.example.SomeFlower.config.annotation.CurrentUser;
import com.example.SomeFlower.config.resTemplate.ResponseTemplate;
import com.example.SomeFlower.domain.userGroup.member.Member;
import com.example.SomeFlower.domain.userGroup.member.dto.MemberDto;
import com.example.SomeFlower.service.member.MemberService;
import com.example.SomeFlower.util.AuthToken;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static com.example.SomeFlower.constant.ResponseTemplateStatus.SUCCESS;

@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
public class MemberSignController {

    private final MemberService memberService;

    /**
     * 일반 회원가입 : id 값 반환
     */
    @PostMapping("/join")
    public ResponseTemplate<Long> createMember(@RequestBody MemberDto.JoinDto joinDto) throws ResponseStatusException {
        return ResponseTemplate.valueOf(memberService.join(joinDto));
    }

    /**
     * 로그인 : 토큰을 발급받아서 반환
     */
    @PostMapping("/login")
    public ResponseEntity<ResponseTemplate<String>> loginMember(@RequestBody MemberDto.LoginDto loginDto) throws ResponseStatusException {
        AuthToken token = memberService.login(loginDto);
        return ResponseEntity.ok()
                .headers(HttpHeaders.readOnlyHttpHeaders(token.asHeaders()))
                .body(ResponseTemplate.of(SUCCESS));
    }

    /**
     * 회원 탈퇴 : 유저의 상태를 변경
     */
    @DeleteMapping("/withdrawl")
    public ResponseTemplate<String> withDrawl(@CurrentUser Member member) {
        memberService.changeMemberStatus(member.getId());
        return ResponseTemplate.of(SUCCESS);
    }
}
