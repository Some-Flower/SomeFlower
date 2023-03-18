package com.example.SomeFlower.controller.member;

import com.example.SomeFlower.config.resTemplate.ResponseTemplate;
import com.example.SomeFlower.domain.member.MemberStatus;
import com.example.SomeFlower.domain.member.dto.MemberDto;
import com.example.SomeFlower.service.member.MemberService;
import com.example.SomeFlower.util.AuthToken;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import static com.example.SomeFlower.constant.ResponseTemplateStatus.SUCCESS;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    /**
     * 일반 회원가입
     */
    @PostMapping("/join")
    public ResponseTemplate<Void> createMember(@RequestBody MemberDto memberDto) throws Exception {
        memberService.join(memberDto);
        return ResponseTemplate.of(SUCCESS);
    }

    /**
     * 유저 정보 수정
     */
    @PatchMapping("/correct")
    public ResponseTemplate<Void> updateMember(@RequestBody MemberDto memberDto,
                                               @AuthenticationPrincipal MemberDto currentMember){
        memberService.updateMember(currentMember.getId(),memberDto);
        return ResponseTemplate.of(SUCCESS);
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<ResponseTemplate<String>> login(@RequestBody MemberDto.MemberLoginDto memberLoginDto) throws ResponseStatusException{
        AuthToken authToken = memberService.login(memberLoginDto);
        return ResponseEntity.ok()
                .headers(HttpHeaders.readOnlyHttpHeaders(authToken.asHeaders()))
                .body(ResponseTemplate.valueOf("ok"));
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
