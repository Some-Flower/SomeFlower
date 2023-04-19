package com.example.SomeFlower.controller.userGroup.seller;


import com.example.SomeFlower.config.resTemplate.ResponseTemplate;
import com.example.SomeFlower.domain.userGroup.member.dto.MemberDto;
import com.example.SomeFlower.domain.userGroup.seller.dto.SellerJoinDto;
import com.example.SomeFlower.domain.userGroup.seller.dto.SellerLoginDto;
import com.example.SomeFlower.service.userGroup.seller.SellerService;
import com.example.SomeFlower.util.AuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static com.example.SomeFlower.constant.ResponseTemplateStatus.SUCCESS;

@RequiredArgsConstructor
@RequestMapping("/api/seller")
@RestController
public class SellerSignController {

    private final SellerService sellerService;

    /**
     * 일반 회원가입
     */
    @PostMapping("/join")
    public ResponseTemplate<Long> createSeller(@RequestBody SellerJoinDto sellerJoinDto) throws ResponseStatusException {
        return ResponseTemplate.valueOf(sellerService.join(sellerJoinDto));
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<ResponseTemplate<String>> loginSeller(@RequestBody SellerLoginDto sellerLoginDto) throws ResponseStatusException {
        AuthToken token = sellerService.login(sellerLoginDto);
        return ResponseEntity.ok()
                .headers(HttpHeaders.readOnlyHttpHeaders(token.asHeaders()))
                .body(ResponseTemplate.valueOf("login success"));
    }
}