package com.example.SomeFlower.domain.plant.repository;

import com.example.SomeFlower.domain.plant.Bulb;
import com.example.SomeFlower.domain.plant.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowerRepository extends JpaRepository<Flower,Long> {
}
