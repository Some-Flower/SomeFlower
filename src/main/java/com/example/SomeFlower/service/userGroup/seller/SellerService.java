package com.example.SomeFlower.service.userGroup.seller;

import com.example.SomeFlower.config.annotation.Validation;
import com.example.SomeFlower.config.resTemplate.ResponseException;
import com.example.SomeFlower.constant.ResponseTemplateStatus;
import com.example.SomeFlower.domain.flowerShop.*;
import com.example.SomeFlower.domain.userGroup.member.dto.MemberDto;
import com.example.SomeFlower.domain.userGroup.seller.Seller;
import com.example.SomeFlower.domain.userGroup.seller.dto.*;
import com.example.SomeFlower.domain.userGroup.seller.repository.SellerRepository;
import com.example.SomeFlower.util.AuthToken;
import com.example.SomeFlower.util.JwtService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.SomeFlower.constant.ResponseTemplateStatus.LOGIN_USER_ERROR;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SellerService {

    private final FlowerShopRepository flowerShopRepository;
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
        Seller seller = SellerAndDtoAdapter.dtoToEntity(sellerJoinDto);

        List<FlowerShopJoinDto> flowerShopDtoList = sellerJoinDto.getFlowerShops();
        List<FlowerShop> flowerShopList = FlowerShopAndDtoAdapter.dtoToEntity(flowerShopDtoList);

        seller.getFlowerShops().addAll(flowerShopList);

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
    public AuthToken login(SellerLoginDto sellerLoginDto){
        try{
            Seller seller = sellerRepository.findByEmail(sellerLoginDto.getEmail()).orElseThrow(NullPointerException::new);
            if (passwordEncoder.matches(sellerLoginDto.getPwd(), seller.getPwd())){
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                sellerLoginDto.getEmail(),
                                sellerLoginDto.getPwd()
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

    /**
     * 판매자 정보 수정
     */
    @Transactional
    @Validation
    public Seller updateSeller(Long id, SellerUpdateDto sellerUpdateDto){
        Seller seller = sellerRepository.findById(id).get();
        seller.update(sellerUpdateDto);
        return seller;
    }
    /**
     * 판매자 꽃집 정보 수정
     */
    @Transactional
    @Validation
    public List<FlowerShop> updateFlowerShop(Long sellerId, List<FlowerShopUpdateDto> flowerShopUpdateDto){
        for(FlowerShopUpdateDto updateDto : flowerShopUpdateDto){
            FlowerShop flowerShop = flowerShopRepository.findById(updateDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("꽃집 엔티티를 찾을 수 없습니다."));
            flowerShop.update(updateDto);
        }
        List<FlowerShop> flowerShops = flowerShopRepository.findBySellerId(sellerId);
        return flowerShops;
    }

}
