package com.example.SomeFlower.service.plant;

import com.example.SomeFlower.domain.plant.dto.PlantDto;
import org.springframework.stereotype.Service;

@Service
public interface PlantService {
    PlantDto savePlantInfo(PlantDto plantDto);
}
