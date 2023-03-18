package com.example.SomeFlower.service.plant;

import com.example.SomeFlower.domain.plant.Flower;
import com.example.SomeFlower.domain.plant.PlantAdapter;
import com.example.SomeFlower.domain.plant.dto.FlowerDto;
import com.example.SomeFlower.domain.plant.dto.PlantDto;
import com.example.SomeFlower.domain.plant.repository.FlowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FlowerService implements PlantService {

    private final FlowerRepository flowerRepository;

    @Override
    public PlantDto savePlantInfo(PlantDto plantDto) {
        FlowerDto flowerDto = (FlowerDto) plantDto;
        Flower flower = PlantAdapter.toEntity(flowerDto);
        flowerRepository.save(flower);
        return PlantAdapter.toDto(flower);
    }



}
