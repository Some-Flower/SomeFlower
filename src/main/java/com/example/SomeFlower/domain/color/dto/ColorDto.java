package com.example.SomeFlower.domain.color.dto;

import com.example.SomeFlower.domain.color.Color;

public class ColorDto {

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

    public Color toEntity(){
        return Color.builder()
                .id(this.id)
                .colorName(this.colorName)
                .hexCode(this.hexCode)
                .redHex(this.redHex)
                .greenHex(this.greenHex)
                .blueHex(this.blueHex)
                .redDecimal(this.redDecimal)
                .greenDecimal(this.greenDecimal)
                .blueDecimal(this.blueDecimal)
                .imgUrl(this.imgUrl)
                .build();
    }

}
