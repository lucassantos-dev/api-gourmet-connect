package com.lucas.gourmet_connect.dto;

import com.lucas.gourmet_connect.entities.RecipeCategory;
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
    private List<String> instructions;
    private Integer prepTime;
    private DifficultyDTO difficulty;
    private OriginDTO origin;
    private Set<RecipeCategory> categories;
    private Set<RecipeIngredientsDTO> ingredients;
}
