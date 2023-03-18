package com.example.SomeFlower.domain.plant.repository;

import com.example.SomeFlower.domain.plant.Bulb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BulbRepository extends JpaRepository<Bulb,Long> {

}
