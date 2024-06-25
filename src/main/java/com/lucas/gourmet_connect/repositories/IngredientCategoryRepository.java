package com.lucas.gourmet_connect.repositories;


import com.lucas.gourmet_connect.domain.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, UUID> {
}
