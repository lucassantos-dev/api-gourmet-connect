package com.lucas.gourmet_connect.mapper;
import com.lucas.gourmet_connect.entities.Ingredient;
import com.lucas.gourmet_connect.entities.IngredientCategory;
import com.lucas.gourmet_connect.dto.IngredientDTO;
import com.lucas.gourmet_connect.dto.IngredientRecipesDTO;
import com.lucas.gourmet_connect.dto.SubstituteDTO;
import com.lucas.gourmet_connect.entities.RecipeIngredients;
import org.springframework.beans.factory.annotation.Autowired;
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
        dto.setSubstitutes(substituteDTOs);
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

public static Ingredient toEntity(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientDTO.getId());
        ingredient.setName(ingredientDTO.getName());
        ingredient.setDescription(ingredientDTO.getDescription());
        ingredient.setUnit(ingredientDTO.getUnit());
        ingredient.setImageUrl(ingredientDTO.getImageUrl());
        ingredient.setOrigin(OriginMapper.toEntity(ingredientDTO.getOrigin()));
        if (ingredientDTO.getCategories() != null) {
            Set<IngredientCategory> categories = ingredientDTO.getCategories().stream()
                    .map(category -> {
                        IngredientCategory categoryEntity = new IngredientCategory();
                        categoryEntity.setId(category.getId());
                        categoryEntity.setName(category.getName());
                        return categoryEntity;
                    })
                    .collect(Collectors.toSet());
            ingredient.setCategories(categories);
        }
        if (ingredientDTO.getSubstitutes() != null) {
            Set<Ingredient> substitutes = ingredientDTO.getSubstitutes().stream()
                    .map(substituteDTO -> {
                        Ingredient substitute = new Ingredient();
                        substitute.setId(substituteDTO.getId());
                        substitute.setName(substituteDTO.getName());
                        return substitute;
                    })
                    .collect(Collectors.toSet());
            ingredient.setSubstitutes(substitutes);
        }
        if (ingredientDTO.getRecipes() != null) {
            Set<RecipeIngredients> recipeIngredients = ingredientDTO.getRecipes().stream()
                    .map(recipeDTO -> {
                        RecipeIngredients recipeIngredient = new RecipeIngredients();
                        recipeIngredient.setId(recipeDTO.getId());
                        return recipeIngredient;
                    })
                    .collect(Collectors.toSet());
            ingredient.setRecipes(recipeIngredients);
        }
        return ingredient;
    }


}
