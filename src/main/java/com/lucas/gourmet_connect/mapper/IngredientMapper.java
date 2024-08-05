package com.lucas.gourmet_connect.mapper;

import com.lucas.gourmet_connect.entities.Ingredient;
import com.lucas.gourmet_connect.entities.IngredientCategory;
import com.lucas.gourmet_connect.dto.IngredientDTO;
import com.lucas.gourmet_connect.dto.IngredientRecipesDTO;
import com.lucas.gourmet_connect.dto.SubstituteDTO;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class IngredientMapper {

/**
 * Converts an Ingredient object to an IngredientDTO object.
 *
 * @param ingredient The Ingredient object to convert.
 * @return The converted IngredientDTO object.
 */
public static IngredientDTO toDTO(Ingredient ingredient) {
    // Create a new IngredientDTO object
    IngredientDTO dto = new IngredientDTO();

    // Set the properties of the IngredientDTO object
    dto.setId(ingredient.getId());
    dto.setName(ingredient.getName());
    dto.setDescription(ingredient.getDescription());
    dto.setUnit(ingredient.getUnit());
    dto.setImageUrl(ingredient.getImageUrl());
    dto.setOrigin(OriginMapper.toDTO(ingredient.getOrigin()));
    // Convert the substitutes set to a set of SubstituteDTO objects
    if (ingredient.getSubstitutes() != null) {
        Set<SubstituteDTO> substituteDTOs = ingredient.getSubstitutes().stream()

                .map(substitute -> {
                    SubstituteDTO substituteDTO = new SubstituteDTO();
                    substituteDTO.setId(substitute.getId());
                    substituteDTO.setName(substitute.getName());
                    return substituteDTO;
                })
                .collect(Collectors.toSet());
        dto.setSubstitute(substituteDTOs);

    }
    if (ingredient.getCategories() != null) {
        Set<IngredientCategory> categories = ingredient.getCategories().stream()
                .map(category -> {
                    IngredientCategory categoryDTO = new IngredientCategory();
                    categoryDTO.setId(category.getId());
                    categoryDTO.setName(category.getName());
                    return categoryDTO;
                })
                .collect(Collectors.toSet());
        dto.setCategories(categories);
    }


    if (ingredient.getRecipes() != null) {
        Set<IngredientRecipesDTO> recipeIngredientsDTOS = ingredient.getRecipes().stream()
                .map(recipes -> {
                    IngredientRecipesDTO ingredientsDTO = new IngredientRecipesDTO();
                    ingredientsDTO.setId(recipes.getId());
                    ingredientsDTO.setName(recipes.getRecipe().getName());
                    return ingredientsDTO;
                })
                .collect(Collectors.toSet());
        dto.setRecipes(recipeIngredientsDTOS);
    }

    // Return the converted IngredientDTO object
    return dto;
}

}
