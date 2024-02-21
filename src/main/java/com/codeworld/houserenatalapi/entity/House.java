package com.codeworld.houserenatalapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "house_seq")
    @SequenceGenerator(name = "house_seq", sequenceName = "HOUSE_SEQ", allocationSize = 1)
    private Long id;
    private String title;
    private String description;
    private String location;
    private double price;
    private int bedrooms;
    private int bathrooms;
    private double size;

}
