package com.lucas.gourmet_connect.services;

import com.lucas.gourmet_connect.domain.Ingredient;
import com.lucas.gourmet_connect.repositories.IngredientRepository;
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
public class IngredientService {
    @Autowired
    private IngredientRepository repository;

    public List<Ingredient> findAll() {
        return repository.findAll();
    }

    public Ingredient findById(UUID id) {
        Optional<Ingredient> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Ingredient insert(Ingredient obj) {
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

    public Ingredient update(UUID id, Ingredient ingredient) {
        try {
            Ingredient entity = repository.getReferenceById(id);
            updateIngredient(entity, ingredient);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateIngredient(Ingredient entity, Ingredient obj) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setOrigin(obj.getOrigin());
        entity.setCategories(obj.getCategories());
    }
}

