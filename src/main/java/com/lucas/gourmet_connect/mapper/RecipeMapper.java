package com.lucas.gourmet_connect.mapper;

import com.lucas.gourmet_connect.dto.IngredientDTO;
import com.lucas.gourmet_connect.dto.RecipeIngredientsDTO;
import com.lucas.gourmet_connect.entities.Ingredient;
import com.lucas.gourmet_connect.entities.Recipe;
import com.lucas.gourmet_connect.dto.RecipeDTO;
import com.lucas.gourmet_connect.entities.RecipeCategory;
import com.lucas.gourmet_connect.entities.RecipeIngredients;
import com.lucas.gourmet_connect.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RecipeMapper {
    @Autowired
    IngredientService service;

    public static RecipeDTO toDTO(Recipe recipe) {
        RecipeDTO dto = new RecipeDTO();
        dto.setId(recipe.getId());
        dto.setName(recipe.getName());
        dto.setDescription(recipe.getDescription());
        dto.setImageUrl(recipe.getImageUrl());
        dto.setDifficulty(DifficultyMapper.toDTO(recipe.getDifficulty()));
        dto.setOrigin(OriginMapper.toDTO(recipe.getOrigin()));
        dto.setPrepTime(recipe.getPrepTime());
        dto.setInstructions(recipe.getInstructions());
        if (recipe.getRecipeIngredients() != null) {
            Set<RecipeIngredientsDTO> ingredientDTOs = recipe.getRecipeIngredients().stream()
                    .map(recipeIngredient -> {
                        RecipeIngredientsDTO ingredientDTO = new RecipeIngredientsDTO();
                        ingredientDTO.setId(recipeIngredient.getIngredient().getId());
                        ingredientDTO.setQuantity(recipeIngredient.getQuantity());
                        IngredientDTO ingredient = new IngredientDTO();
                        ingredient.setId(recipeIngredient.getIngredient().getId());
                        ingredient.setName(recipeIngredient.getIngredient().getName());
                        ingredient.setUnit(recipeIngredient.getIngredient().getUnit());
                        ingredient.setImageUrl(recipeIngredient.getIngredient().getImageUrl());
                        ingredientDTO.setIngredient(ingredient);
                        return ingredientDTO;
                    })
                    .collect(Collectors.toSet());
            dto.setIngredients(ingredientDTOs);
        }
        if (recipe.getCategories() != null) {
            Set<RecipeCategory> categoryDTOs = recipe.getCategories().stream()
                    .map(category -> {
                        RecipeCategory categoryDTO = new RecipeCategory();
                        categoryDTO.setId(category.getId());
                        categoryDTO.setName(category.getName());
                        return categoryDTO;
                    })
                    .collect(Collectors.toSet());
            dto.setCategories(categoryDTOs);
        }
        return dto;
    }
    public Recipe toEntity(RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe();
        recipe.setId(recipeDTO.getId());
        recipe.setName(recipeDTO.getName());
        recipe.setDescription(recipeDTO.getDescription());
        recipe.setImageUrl(recipeDTO.getImageUrl());
        recipe.setDifficulty(DifficultyMapper.toEntity(recipeDTO.getDifficulty()));
        recipe.setOrigin(OriginMapper.toEntity(recipeDTO.getOrigin()));
        recipe.setPrepTime(recipeDTO.getPrepTime());
        recipe.setInstructions(recipeDTO.getInstructions());
        if (recipeDTO.getCategories() != null) {
            Set<RecipeCategory> categories = recipeDTO.getCategories().stream()
                    .map(categoryDTO -> {
                        RecipeCategory category = new RecipeCategory();
                        category.setId(categoryDTO.getId());
                        category.setName(categoryDTO.getName());
                        return category;
                    })
                    .collect(Collectors.toSet());
            recipe.setCategories(categories);
        }
        if (recipeDTO.getIngredients() != null) {
            Set<RecipeIngredients> ingredients = recipeDTO.getIngredients().stream()
                    .map(ingredientDTO -> {
                        RecipeIngredients recipeIngredient = new RecipeIngredients();
                        recipeIngredient.setQuantity(ingredientDTO.getQuantity());
                        Ingredient ingredient = service.findById2(ingredientDTO.getIngredient().getId());
                        recipeIngredient.setIngredient(ingredient);
                        recipeIngredient.setRecipe(recipe);
                        return recipeIngredient;
                    })
                    .collect(Collectors.toSet());
            recipe.setRecipeIngredients(ingredients);
        }
        return recipe;
        }
    }
