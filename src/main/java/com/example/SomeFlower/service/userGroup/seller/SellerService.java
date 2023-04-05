package com.example.SomeFlower.service.userGroup.seller;

import com.example.SomeFlower.config.annotation.Validation;
import com.example.SomeFlower.config.resTemplate.ResponseException;
import com.example.SomeFlower.constant.ResponseTemplateStatus;
import com.example.SomeFlower.domain.flowerShop.FlowerShop;
import com.example.SomeFlower.domain.flowerShop.FlowerShopDto;
import com.example.SomeFlower.domain.userGroup.member.dto.MemberDto;
import com.example.SomeFlower.domain.userGroup.seller.Seller;
import com.example.SomeFlower.domain.userGroup.seller.dto.SellerAdapter;
import com.example.SomeFlower.domain.userGroup.seller.dto.SellerJoinDto;
import com.example.SomeFlower.domain.userGroup.seller.dto.SellerLoginDto;
import com.example.SomeFlower.domain.userGroup.seller.repository.SellerRepository;
import com.example.SomeFlower.util.AuthToken;
import com.example.SomeFlower.util.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.SomeFlower.constant.ResponseTemplateStatus.LOGIN_USER_ERROR;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SellerService {

    private final SellerRepository sellerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * 회원가입
     */
    @Transactional
    @Validation
    public Long join(SellerJoinDto sellerJoinDto) {
        Seller seller = sellerJoinDto.asEntity();
        //꽃집 등록 정보를 직접 넣음
        List<FlowerShopDto> flowerShops = sellerJoinDto.getFlowerShops();
        flowerShops.forEach(flowerShop -> seller.putFlowerShop(
                FlowerShop.createFlowerShop(flowerShop.getShopName(),
                                            flowerShop.getDescription(),
                                            flowerShop.getShopImage(),
                                            flowerShop.getShopLink(),
                                            flowerShop.getBusinessHours(),
                                            flowerShop.getBusinessDates(),
                                            flowerShop.getShopNumber(), seller, flowerShop.getAddress())
        ));

        validateDuplicateEmail(seller.getEmail());
        seller.setPwd(passwordEncoder.encode(seller.getPwd()));
        sellerRepository.save(seller);
        return seller.getId();
    }

    /**
     * 로그인
     */
    @Transactional
    @Validation
    public AuthToken login(MemberDto.LoginDto loginDto){
        try{
            Seller seller = sellerRepository.findByEmail(loginDto.getEmail()).orElseThrow(NullPointerException::new);
            if (passwordEncoder.matches(loginDto.getPwd(), seller.getPwd())){
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginDto.getEmail(),
                                loginDto.getPwd()
                        )
                );
                AuthToken authToken = jwtService.generateToken(new SellerAdapter(seller));
                return authToken;
            }
            throw new ResponseException(LOGIN_USER_ERROR);
        }
        catch (NullPointerException e){
            e.printStackTrace();
            throw new ResponseException(LOGIN_USER_ERROR);
        }
    }

    /**
     * 이메일 중복 검사
     */
    public void validateDuplicateEmail(String email) throws ResponseException {
        if (sellerRepository.findByEmail(email).isPresent()){
            throw new ResponseException(ResponseTemplateStatus.EMAIL_DUPLICATE);
        }
    }

}
