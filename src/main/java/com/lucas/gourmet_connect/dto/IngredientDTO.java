package com.lucas.gourmet_connect.dto;

import com.lucas.gourmet_connect.entities.IngredientCategory;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class IngredientDTO {

    private UUID id;
    private String name;
    private String description;
    private String unit;
    private String imageUrl;
    private OriginDTO origin;
    private Set<IngredientCategory> categories;
    private Set<SubstituteDTO> substitute;
    private Set<IngredientRecipesDTO> recipes;
}
