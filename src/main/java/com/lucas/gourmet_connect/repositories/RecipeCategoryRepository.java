package com.lucas.gourmet_connect.repositories;


import com.lucas.gourmet_connect.domain.RecipeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecipeCategoryRepository extends JpaRepository<RecipeCategory, UUID> {
}
