package com.example.SomeFlower.controller.userGroup.seller;

import com.example.SomeFlower.config.annotation.CurrentSeller;
import com.example.SomeFlower.config.resTemplate.ResponseTemplate;
import com.example.SomeFlower.domain.flowerShop.FlowerShop;
import com.example.SomeFlower.domain.flowerShop.FlowerShopUpdateDto;
import com.example.SomeFlower.domain.userGroup.seller.Seller;
import com.example.SomeFlower.domain.userGroup.seller.dto.SellerUpdateDto;
import com.example.SomeFlower.service.userGroup.seller.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller")
public class SellerController {
    private final SellerService sellerService;

    /**
     * 판매자 정보 조회
     */
    @GetMapping("/getInfo")
    public ResponseTemplate<Optional<Seller>> getSellerInfo(@CurrentSeller Seller seller){
        return ResponseTemplate.valueOf(Optional.ofNullable(seller));
    }

    /**
     * 판매자 정보 수정
     */
    @PutMapping("/updateSellerInfo")
    public ResponseTemplate<Seller> updateSeller(@RequestBody SellerUpdateDto sellerUpdateDto,
                                                 @CurrentSeller Seller seller) throws ResponseStatusException{
        Seller updateSeller = sellerService.updateSeller(seller.getId(), sellerUpdateDto);
        return ResponseTemplate.valueOf(updateSeller);
    }

    @PutMapping("/updateFlowerShopInfo")
    public ResponseTemplate<List<FlowerShop>> updateSeller(@RequestBody List<FlowerShopUpdateDto>
                                                             flowerShopUpdateDto,
                                                 @CurrentSeller Seller seller) throws ResponseStatusException{
        List<FlowerShop> updatedFlowerShop = sellerService.updateFlowerShop(seller.getId(), flowerShopUpdateDto);
        return ResponseTemplate.valueOf(updatedFlowerShop);
    }
}
