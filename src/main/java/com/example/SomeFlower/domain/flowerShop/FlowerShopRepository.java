package com.example.SomeFlower.domain.flowerShop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlowerShopRepository extends JpaRepository<FlowerShop, Long> {
    List<FlowerShop> findBySellerId(Long sellerId);
}
