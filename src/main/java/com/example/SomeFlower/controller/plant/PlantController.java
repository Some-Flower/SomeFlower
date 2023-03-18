package com.example.SomeFlower.controller.plant;

import com.example.SomeFlower.config.resTemplate.ResponseTemplate;
import com.example.SomeFlower.domain.plant.Plant;
import com.example.SomeFlower.domain.plant.dto.PlantDto;
import com.example.SomeFlower.service.plant.PlantFactory;
import com.example.SomeFlower.service.plant.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import static com.example.SomeFlower.constant.ResponseTemplateStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plant")
public class PlantController {

    private final PlantFactory plantFactory;

    /**
     * 등록
     */
    @PostMapping("/register")
    public ResponseTemplate<Void> register(@RequestBody PlantDto plantDto) throws ResponseStatusException{
        PlantService plantService = plantFactory.getType(plantDto.getPlantType());
        plantService.savePlantInfo(plantDto);
        return ResponseTemplate.of(SUCCESS);

    }
}
