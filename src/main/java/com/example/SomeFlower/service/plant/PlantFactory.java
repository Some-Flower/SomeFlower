package com.example.SomeFlower.service.plant;

import com.example.SomeFlower.domain.plant.data.PlantType;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import static com.example.SomeFlower.domain.plant.data.PlantType.*;

@Component
public class PlantFactory {

    private final Map<PlantType,PlantService> map;

    public PlantFactory(FleshyService fleshyService,FlowerService flowerService,
                        PotService potService,BulbService bulbService) {
        map = new HashMap<>();
        map.put(FLESHY,fleshyService);
        map.put(FLOWER,flowerService);
        map.put(POT,potService);
        map.put(BULB,bulbService);
    }

    public PlantService getType(PlantType plantType){
        if(map.containsKey(plantType)){
            return map.get(plantType);
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
