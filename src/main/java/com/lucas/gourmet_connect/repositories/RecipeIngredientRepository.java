package com.lucas.gourmet_connect.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucas.gourmet_connect.domain.RecipeIngredient;
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, UUID> {
  
}
