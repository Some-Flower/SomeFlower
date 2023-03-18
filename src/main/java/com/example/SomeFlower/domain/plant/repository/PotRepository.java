package com.example.SomeFlower.domain.plant.repository;

import com.example.SomeFlower.domain.plant.Bulb;
import com.example.SomeFlower.domain.plant.Pot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PotRepository extends JpaRepository<Pot,Long> {
}
