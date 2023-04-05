package com.example.SomeFlower.domain.userGroup.seller.repository;

import com.example.SomeFlower.domain.userGroup.Status;
import com.example.SomeFlower.domain.userGroup.seller.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    Optional<Seller> findByEmail(@Param("email") String email);
    Page<Seller> findMemberByStatus(Status status, Pageable pageable);

}
