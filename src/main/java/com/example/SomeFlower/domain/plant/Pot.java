package com.example.SomeFlower.domain.plant;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pot {

    @Id @GeneratedValue
    @Column(name = "pot_id")
    private Long id;

    private int width;
    private int height;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_id")
    private Plant plant;
}
