package com.codeworld.houserenatalapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class HouseDto {
    private Long id;
    private String title;
    private String description;
    private String location;
    private double price;
    private int bedrooms;
    private int bathrooms;
    private double size;

}
