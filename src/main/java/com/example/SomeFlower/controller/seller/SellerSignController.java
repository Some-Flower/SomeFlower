//package com.example.SomeFlower.controller.seller;
//
//
//import com.example.SomeFlower.config.resTemplate.ResponseTemplate;
//import com.example.SomeFlower.util.AuthToken;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;
//
//import static com.example.SomeFlower.constant.ResponseTemplateStatus.SUCCESS;
//
//@RequiredArgsConstructor
//@RequestMapping("/api/seller")
//@RestController
//public class SellerSignController {
//
//    private final SellerService sellerService;
//
//    /**
//     * 일반 회원가입
//     */
//    @PostMapping("/join")
//    public ResponseTemplate<Void> createSeller(@RequestBody SellerDto sellerDto) throws Exception {
//        sellerService.join(sellerDto);
//        return ResponseTemplate.of(SUCCESS);
//    }
//
//    /**
//     * 로그인
//     */
//    @PostMapping("/login")
//    public ResponseEntity<ResponseTemplate<String>> loginSeller(@RequestBody SellerDto.LoginDto loginDto) throws ResponseStatusException {
//        AuthToken token = sellerService.login(loginDto);
//        return ResponseEntity.ok()
//                .headers(HttpHeaders.readOnlyHttpHeaders(token.asHeaders()))
//                .body(ResponseTemplate.valueOf("login success"));
//    }
//}