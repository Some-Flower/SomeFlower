package com.example.SomeFlower.service.plant;

import com.example.SomeFlower.domain.plant.data.Fleshy;
import com.example.SomeFlower.domain.plant.dto.PlantAdapter;
import com.example.SomeFlower.domain.plant.dto.FleshyDto;
import com.example.SomeFlower.domain.plant.dto.PlantDto;
import com.example.SomeFlower.domain.plant.repository.FleshyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FleshyService implements PlantService{

    private final FleshyRepository fleshyRepository;
    
    @Override
    public PlantDto savePlantInfo(PlantDto plantDto) {
        FleshyDto fleshyDto = (FleshyDto) plantDto;
        Fleshy fleshy = PlantAdapter.toEntity(fleshyDto);
        fleshyRepository.save(fleshy);
        return PlantAdapter.toDto(fleshy);
    }



}
