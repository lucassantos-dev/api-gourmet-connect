package com.lucas.gourmet_connect.dto;

import com.lucas.gourmet_connect.entities.Ingredient;
import lombok.Data;

import java.util.UUID;

@Data
public class RecipeIngredientsDTO {
    private UUID id;
    private Double quantity;
    private IngredientDTO ingredient;
}
