package com.lucas.gourmet_connect.config;

import com.lucas.gourmet_connect.domain.*;
import com.lucas.gourmet_connect.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private DifficultyRepository difficultyRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private OriginRepository originRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeCategoryRepository recipeCategoryRepository;

    @Override
    public void run(String... args) throws Exception{
        // Criando as categorias de ingredientes
        IngredientCategory ingredientCategory1 = new IngredientCategory(null, "Meat", new HashSet<>());
        IngredientCategory ingredientCategory2 = new IngredientCategory(null, "Vegetables", new HashSet<>());
        IngredientCategory ingredientCategory3 = new IngredientCategory(null, "Grains", new HashSet<>());

        // Salvando as categorias de ingredientes no banco de dados
        ingredientCategoryRepository.saveAll(Arrays.asList(ingredientCategory1, ingredientCategory2, ingredientCategory3));

        // Criando as origens
        Origin origin1 = new Origin(null, "Italian", "Traditional Italian cuisine", new HashSet<>());
        Origin origin2 = new Origin(null, "Mexican", "Authentic Mexican flavors", new HashSet<>());

        // Salvando as origens no banco de dados
        originRepository.saveAll(Arrays.asList(origin1, origin2));

        // Criando os ingredientes associados às categorias e origens
        Ingredient ingredient1 = new Ingredient(null, "Chicken", "Fresh chicken breast", origin1, new HashSet<>());
        ingredient1.getCategories().addAll(Arrays.asList(ingredientCategory1, ingredientCategory2));

        Ingredient ingredient2 = new Ingredient(null, "Tomato", "Ripe tomatoes", origin2, new HashSet<>());
        ingredient2.getCategories().add(ingredientCategory2);

        Ingredient ingredient3 = new Ingredient(null, "Rice", "Long grain white rice", origin1, new HashSet<>());
        ingredient3.getCategories().add(ingredientCategory3);

        // Salvando os ingredientes no banco de dados
        ingredientRepository.saveAll(Arrays.asList(ingredient1, ingredient2, ingredient3));

        // Criando as dificuldades
        Difficulty difficulty1 = new Difficulty(null, "Easy", "Simple recipes for beginners", new HashSet<>());
        Difficulty difficulty2 = new Difficulty(null, "Medium", "Intermediate level recipes", new HashSet<>());
        Difficulty difficulty3 = new Difficulty(null, "Hard", "Complex recipes for experienced cooks", new HashSet<>());

        // Salvando as dificuldades no banco de dados
        difficultyRepository.saveAll(Arrays.asList(difficulty1, difficulty2, difficulty3));

        // Criando as categorias de receitas
        RecipeCategory recipeCategory1 = new RecipeCategory(null, "Soup", new HashSet<>());
        RecipeCategory recipeCategory2 = new RecipeCategory(null, "Dessert", new HashSet<>());

        // Salvando as categorias de receitas no banco de dados
        recipeCategoryRepository.saveAll(Arrays.asList(recipeCategory1, recipeCategory2));

        // Criando as receitas associadas às categorias e dificuldades
        Recipe recipe1 = new Recipe(null, "Chicken Soup", "Instructions for making chicken soup", 30, difficulty1, new HashSet<>());
        recipe1.getCategories().add(recipeCategory1);

        Recipe recipe2 = new Recipe(null, "Tomato Salad", "Instructions for making tomato salad", 15, difficulty1, new HashSet<>());
        recipe2.getCategories().add(recipeCategory2);

        // Salvando as receitas no banco de dados
        recipeRepository.saveAll(Arrays.asList(recipe1, recipe2));
    }
}
