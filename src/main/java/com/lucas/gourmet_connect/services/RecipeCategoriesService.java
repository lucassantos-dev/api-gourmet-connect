package com.lucas.gourmet_connect.services;
import com.lucas.gourmet_connect.domain.RecipeCategory;
import com.lucas.gourmet_connect.repositories.RecipeCategoryRepository;
import com.lucas.gourmet_connect.services.exceptions.DatabaseException;
import com.lucas.gourmet_connect.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecipeCategoriesService {
    @Autowired
    private RecipeCategoryRepository repository;

    public List<RecipeCategory> findAll() {
        return repository.findAll();
    }

    public RecipeCategory findById(UUID id) {
        Optional<RecipeCategory> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public RecipeCategory insert(RecipeCategory obj) {
        return repository.save(obj);
    }

    public void delete(UUID id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public RecipeCategory update(UUID id, RecipeCategory category) {
        try {
            RecipeCategory entity = repository.getReferenceById(id);
            updateCategory(entity, category);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateCategory(RecipeCategory entity, RecipeCategory obj) {
        entity.setName(obj.getName());
    }
}
