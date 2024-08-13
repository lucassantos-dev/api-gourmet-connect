package com.lucas.gourmet_connect.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.gourmet_connect.entities.RecipeIngredients;
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredients, UUID> {

}
