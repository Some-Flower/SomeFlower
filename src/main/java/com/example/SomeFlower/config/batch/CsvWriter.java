package com.example.SomeFlower.config.batch;

import com.example.SomeFlower.domain.color.Color;
import com.example.SomeFlower.domain.color.ColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
@RequiredArgsConstructor
public class CsvWriter implements ItemWriter<Color>{

    private final ColorRepository colorRepository;

    @Override
    public void write(Chunk<? extends Color> chunk) throws Exception {
        colorRepository.saveAll(chunk);
    }

}
