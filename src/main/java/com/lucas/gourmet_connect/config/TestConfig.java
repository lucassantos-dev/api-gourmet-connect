package com.lucas.gourmet_connect.config;

import com.lucas.gourmet_connect.entities.*;
import com.lucas.gourmet_connect.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    public static final String URL = "https://img.freepik.com/fotos-gratis/variedade-plana-com-deliciosa-comida-brasileira_23-2148739179.jpg?w=740&t=st=1719511049~exp=1719511649~hmac=fe71fabd82ff12302bfdf8691b3d5b2d65d90139c885112b3af30623f848a7e9";
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

    @Autowired
    private RecipeIngredientRepository recipeIngredientRepository;

    @Override
    public void run(String... args) throws Exception {
        IngredientCategory ingredientCategory1 = new IngredientCategory(null, "Meat", new HashSet<>());
        IngredientCategory ingredientCategory2 = new IngredientCategory(null, "Vegetables", new HashSet<>());
        IngredientCategory ingredientCategory3 = new IngredientCategory(null, "Grains", new HashSet<>());

        ingredientCategoryRepository.saveAll(Arrays.asList(ingredientCategory1, ingredientCategory2, ingredientCategory3));

        Origin origin1 = new Origin(null, "Italian", "Traditional Italian cuisine", new HashSet<>());
        Origin origin2 = new Origin(null, "Mexican", "Authentic Mexican flavors", new HashSet<>());

        originRepository.saveAll(Arrays.asList(origin1, origin2));

        Ingredient ingredient1 = new Ingredient(null, "Chicken", "Fresh chicken breast", "kg",URL,origin1, new HashSet<>(), new HashSet<>(), new HashSet<>());
        ingredient1.getCategories().addAll(Arrays.asList(ingredientCategory1, ingredientCategory2));

        Ingredient ingredient2 = new Ingredient(null, "Tomato", "Ripe tomatoes", "pieces",URL,origin2, new HashSet<>(), new HashSet<>(), new HashSet<>());
        ingredient2.getCategories().add(ingredientCategory2);

        Ingredient ingredient3 = new Ingredient(null, "Rice", "Long grain white rice","kg" ,URL,origin1, new HashSet<>(), new HashSet<>(), new HashSet<>());
        ingredient3.getCategories().add(ingredientCategory3);


        ingredient2.getSubstitutes().add(ingredient1);
        ingredient2.getSubstitutes().add(ingredient3);
        ingredient3.getSubstitutes().add(ingredient1);
        ingredient1.getSubstitutes().add(ingredient3);
//
        ingredientRepository.saveAll(Arrays.asList(ingredient1, ingredient2, ingredient3));


        Difficulty difficulty1 = new Difficulty(null, "Easy", new HashSet<>());
        Difficulty difficulty2 = new Difficulty(null, "Medium", new HashSet<>());
        Difficulty difficulty3 = new Difficulty(null, "Hard", new HashSet<>());

        difficultyRepository.saveAll(Arrays.asList(difficulty1, difficulty2, difficulty3));

        RecipeCategory recipeCategory1 = new RecipeCategory(null, "Soup", new HashSet<>());
        RecipeCategory recipeCategory2 = new RecipeCategory(null, "Dessert", new HashSet<>());
        List<String> instructions1 = Arrays.asList(
                "4 ounces bittersweet chocolate, chopped",
                "1/2 cup unsalted butter",
                "1/4 cup granulated sugar",
                "2 large eggs",
                "2 large egg yolks",
                "2 tablespoons all-purpose flour",
                "Pinch of salt",
                "Powdered sugar, for dusting"
        );

        recipeCategoryRepository.saveAll(Arrays.asList(recipeCategory1, recipeCategory2));

        Recipe recipe1 = new Recipe(null, "Chicken Soup", "Sopa de frango", URL, instructions1, 30,origin1 ,difficulty1, new HashSet<>(), new HashSet<>());
        recipe1.getCategories().add(recipeCategory1);

        Recipe recipe2 = new Recipe(null, "Tomato Salad", "Salada de tomate", URL, instructions1, 15,origin1, difficulty1, new HashSet<>(), new HashSet<>());
        recipe2.getCategories().add(recipeCategory2);

        Recipe recipe3 = new Recipe(null, "Tomato", "Salada de tomate", URL, instructions1, 15,origin1, difficulty1, new HashSet<>(), new HashSet<>());
        recipe3.getCategories().add(recipeCategory1);
        recipe3.getCategories().add(recipeCategory2);

        Recipe recipe4 = new Recipe(null, "Salad", "Salada de tomate", URL, instructions1, 15,origin1, difficulty1, new HashSet<>(), new HashSet<>());
        recipe4.getCategories().add(recipeCategory2);

        Recipe recipe5 = new Recipe(null, "Tomato Salad 2", "Salada de tomate", URL, instructions1, 15,origin1, difficulty1, new HashSet<>(), new HashSet<>());
        recipe5.getCategories().add(recipeCategory2);

        recipeRepository.saveAll(Arrays.asList(recipe1, recipe2, recipe3, recipe4, recipe5));

        // Create RecipeIngredient relationships
        RecipeIngredients recipeIngredients1 = new RecipeIngredients(null, recipe1, ingredient1, 0.5);
        RecipeIngredients recipeIngredients2 = new RecipeIngredients(null, recipe1, ingredient2, 3.0);
        RecipeIngredients recipeIngredients3 = new RecipeIngredients(null, recipe2, ingredient2, 4.0);
        RecipeIngredients recipeIngredients4 = new RecipeIngredients(null, recipe3, ingredient3, 1.0);
        RecipeIngredients recipeIngredients5 = new RecipeIngredients(null, recipe4, ingredient3, 2.0);

        recipeIngredientRepository.saveAll(Arrays.asList(recipeIngredients1, recipeIngredients2, recipeIngredients3, recipeIngredients4, recipeIngredients5));
    }
}
