package com.example.SomeFlower.domain.plant.repository;

import com.example.SomeFlower.domain.plant.data.Fleshy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FleshyRepository extends JpaRepository<Fleshy,Long> {
}
