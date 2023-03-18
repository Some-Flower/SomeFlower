package com.example.SomeFlower.domain.plant.repository;

import com.example.SomeFlower.domain.plant.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlantRepository extends JpaRepository<Plant,Long> {
    Optional<Plant> findByName(@Param("Name") String Name);
}
