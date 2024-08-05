package com.lucas.gourmet_connect.repositories;

import com.lucas.gourmet_connect.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecipeRepository extends JpaRepository<Recipe, UUID> {
}
