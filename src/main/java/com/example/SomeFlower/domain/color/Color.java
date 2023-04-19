package com.example.SomeFlower.domain.color;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Builder @Entity @Embeddable
@RequiredArgsConstructor
@AllArgsConstructor
public class Color {

    @Id
    private Long id;
    private String colorName;
    private String hexCode;
    private String redHex;
    private String greenHex;
    private String blueHex;
    private String redDecimal;
    private String greenDecimal;
    private String blueDecimal;
    private String imgUrl;

}
