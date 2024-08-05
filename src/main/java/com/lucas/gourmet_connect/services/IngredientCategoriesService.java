package com.lucas.gourmet_connect.services;


import com.lucas.gourmet_connect.entities.IngredientCategory;
import com.lucas.gourmet_connect.repositories.IngredientCategoryRepository;

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
public class IngredientCategoriesService {

    @Autowired
    private IngredientCategoryRepository repository;

    public List<IngredientCategory> findAll() {
        return repository.findAll();
    }

    public IngredientCategory findById(UUID id) {
        Optional<IngredientCategory> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public IngredientCategory insert(IngredientCategory obj) {
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

    public IngredientCategory update(UUID id, IngredientCategory category) {
        try {
            IngredientCategory entity = repository.getReferenceById(id);
            updateCategory(entity, category);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateCategory(IngredientCategory entity, IngredientCategory obj) {
        entity.setName(obj.getName());

    }
}
