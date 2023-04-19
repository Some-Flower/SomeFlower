package com.example.SomeFlower.config.batch;

import com.example.SomeFlower.domain.color.Color;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class FileReaderConfig {

    private final CsvReader csvReader;
    private final CsvWriter csvWriter;

    private static final int chunkSize = 800;

    @Bean
    public Job csvFileReaderJob(JobRepository jobRepository,PlatformTransactionManager transactionManager){
        return new JobBuilder("Color", jobRepository)
                .start(csvFileReaderStep(jobRepository,transactionManager))
                .build();
    }

    @Bean
    public Step csvFileReaderStep(JobRepository jobRepository,PlatformTransactionManager transactionManager) {
        return new StepBuilder("csvFileReaderStep",jobRepository)
                .<Color, Color>chunk(chunkSize,transactionManager)
                .reader(csvReader.csvFileItemReader())
                .writer(csvWriter)
                .build();
    }

}
