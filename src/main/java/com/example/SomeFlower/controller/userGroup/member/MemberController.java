package com.example.SomeFlower.controller.userGroup.member;

import com.example.SomeFlower.config.annotation.CurrentUser;
import com.example.SomeFlower.config.resTemplate.ResponseTemplate;
import com.example.SomeFlower.domain.userGroup.member.Member;
import com.example.SomeFlower.domain.userGroup.member.dto.MemberDto;
import com.example.SomeFlower.service.userGroup.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    /**
     * 유저 정보 조회
     */
    @GetMapping("/getInfo")
    public ResponseTemplate<Optional<Member>> getMemberInfo(@CurrentUser Member member){
        return ResponseTemplate.valueOf(Optional.ofNullable(member));
    }

    /**
     * 유저 정보 수정
     */
    @PutMapping("/update")
    public ResponseTemplate<Member> updateMember(@RequestBody MemberDto.UpdateDto updateDto,
                                               @CurrentUser Member member)  throws ResponseStatusException {
        Member updateMember = memberService.update(member.getId(), updateDto);
        return ResponseTemplate.valueOf(updateMember);
    }

}
