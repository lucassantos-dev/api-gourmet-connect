package com.lucas.gourmet_connect.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RecipeIngredientsDTO {

    private UUID id;
    private String name;
    private String unit;
    private String imageUrl;

}
