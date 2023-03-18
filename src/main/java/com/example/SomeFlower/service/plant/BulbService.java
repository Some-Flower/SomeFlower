package com.example.SomeFlower.service.plant;

import com.example.SomeFlower.domain.plant.Bulb;
import com.example.SomeFlower.domain.plant.PlantAdapter;
import com.example.SomeFlower.domain.plant.dto.BulbDto;
import com.example.SomeFlower.domain.plant.dto.PlantDto;
import com.example.SomeFlower.domain.plant.repository.BulbRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BulbService implements PlantService{

    private final BulbRepository bulbRepository;

    @Override
    @Transactional
    public PlantDto savePlantInfo(PlantDto plantDto) {
        BulbDto bulbDto = (BulbDto) plantDto;
        Bulb bulb = PlantAdapter.toEntity(bulbDto);
        bulbRepository.save(bulb);
        return PlantAdapter.toDto(bulb);
    }
}
