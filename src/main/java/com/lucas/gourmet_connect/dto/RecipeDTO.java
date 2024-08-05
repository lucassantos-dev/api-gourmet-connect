package com.lucas.gourmet_connect.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class RecipeDTO {

    private UUID id;
    private String name;
    private String description;
    private String imageUrl;
    private DifficultyDTO difficulty;
    private OriginDTO origin;
    private Set<RecipeIngredientsDTO> ingredients;
}
