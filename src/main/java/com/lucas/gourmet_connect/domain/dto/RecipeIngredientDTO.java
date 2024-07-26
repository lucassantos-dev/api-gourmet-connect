package com.lucas.gourmet_connect.domain.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import com.lucas.gourmet_connect.domain.Ingredient;
import com.lucas.gourmet_connect.domain.RecipeIngredient;

@Getter
@Setter
public class RecipeIngredientDTO {
    private UUID id;
    private IngredientDTO ingredient;
    private Double quantity;
    public RecipeIngredientDTO convertToDTO(RecipeIngredient recipeIngredient) {
      RecipeIngredientDTO dto = new RecipeIngredientDTO();
      dto.setId(recipeIngredient.getId());
      dto.setQuantity(recipeIngredient.getQuantity());
      
      IngredientDTO ingredientDTO = new IngredientDTO();
      Ingredient ingredient = recipeIngredient.getIngredient();
      ingredientDTO.setId(ingredient.getId());
      ingredientDTO.setName(ingredient.getName());
      ingredientDTO.setDescription(ingredient.getDescription());
      ingredientDTO.setUnit(ingredient.getUnit());
      ingredientDTO.setImageUrl(ingredient.getImageUrl());
      
      dto.setIngredient(ingredientDTO);
      return dto;
    }

  }
