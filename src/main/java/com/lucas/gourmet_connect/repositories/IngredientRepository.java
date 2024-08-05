package com.lucas.gourmet_connect.repositories;

import com.lucas.gourmet_connect.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {
}
