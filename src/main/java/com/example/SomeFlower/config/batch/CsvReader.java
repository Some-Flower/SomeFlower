package com.example.SomeFlower.config.batch;

import com.example.SomeFlower.domain.color.Color;
import com.example.SomeFlower.domain.color.dto.ColorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@RequiredArgsConstructor
public class CsvReader {
    @Bean
    public FlatFileItemReader<Color> csvFileItemReader(){
        //FlatFileItemReader 객체 생성
        FlatFileItemReader<Color> flatFileItemReader = new FlatFileItemReader<>();
        //src/main/resources/csv/food.csv 경로의 파일 지정
        flatFileItemReader.setResource(new ClassPathResource("/csv/color.csv"));
        //맨 윗줄(header)는 읽지 않고 skip 하겠다는 의미 (맨 윗줄이 제목 줄인 경우 사용)
        flatFileItemReader.setLinesToSkip(1); // header line skip
        //인코딩 지정
        flatFileItemReader.setEncoding("UTF-8"); // encoding

        //매핑을 원하는 엔티티(Food)로 객체를 생성한다.
        DefaultLineMapper<Color> defaultLineMapper = new DefaultLineMapper<>();

        //csv 파일에서 구분자 설정
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(",");

        // 각각의 데이터 이름 설정 - 엔티티 필드의 이름과 동일하게 설정하면 된다.
        delimitedLineTokenizer.setNames("id", "color_name","hex_code","red_hex","green_hex",
                "blue_hex","red_decimal","green_decimal","blue_decimal","img_url");
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);

        /* beanWrapperFieldSetMapper : Tokenizer에서 가지고온 데이터들을 VO로 바인드하는 역할 */
        BeanWrapperFieldSetMapper<Color> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(Color.class);

        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        /* lineMapper 지정 */
        flatFileItemReader.setLineMapper(defaultLineMapper);

        return flatFileItemReader;
    }
}
