package com.lucas.gourmet_connect.mapper;

import com.lucas.gourmet_connect.entities.Recipe;
import com.lucas.gourmet_connect.dto.RecipeIngredientsDTO;
import com.lucas.gourmet_connect.dto.RecipeDTO;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RecipeMapper {
    public static RecipeDTO toDTO(Recipe recipe) {
        RecipeDTO dto = new RecipeDTO();
        dto.setId(recipe.getId());
        dto.setName(recipe.getName());
        dto.setDescription(recipe.getDescription());
        dto.setImageUrl(recipe.getImageUrl());
        dto.setDifficulty(DifficultyMapper.toDTO(recipe.getDifficulty()));
        dto.setOrigin(OriginMapper.toDTO(recipe.getOrigin()));
        if (recipe.getRecipeIngredients() != null) {
            Set<RecipeIngredientsDTO> ingredientDTOs = recipe.getRecipeIngredients().stream()
                            .map( recipeIngredient -> {
                                        RecipeIngredientsDTO ingredientDTO = new RecipeIngredientsDTO();
                                        ingredientDTO.setId(recipeIngredient.getIngredient().getId());
                                        ingredientDTO.setName(recipeIngredient.getIngredient().getName());
                                        ingredientDTO.setUnit(recipeIngredient.getIngredient().getUnit());
                                        ingredientDTO.setImageUrl(recipeIngredient.getIngredient().getImageUrl());
                                        return ingredientDTO;
                                    }

                            ).collect(Collectors.toSet());
            dto.setIngredients(ingredientDTOs);
        }
        return dto;
    }
}
