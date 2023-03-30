package com.example.SomeFlower.service.plant;


import com.example.SomeFlower.domain.plant.dto.PlantAdapter;
import com.example.SomeFlower.domain.plant.data.Pot;
import com.example.SomeFlower.domain.plant.dto.PlantDto;
import com.example.SomeFlower.domain.plant.dto.PotDto;
import com.example.SomeFlower.domain.plant.repository.PotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PotService implements PlantService{

    private final PotRepository potRepository;

    @Override
    public PlantDto savePlantInfo(PlantDto plantDto) {
        PotDto potDto = (PotDto) plantDto;
        Pot pot = PlantAdapter.toEntity(potDto);
        potRepository.save(pot);
        return PlantAdapter.toDto(pot);
    }

}
