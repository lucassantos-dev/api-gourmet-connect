package com.lucas.gourmet_connect.services;
import com.lucas.gourmet_connect.domain.Recipe;
import com.lucas.gourmet_connect.repositories.RecipeRepository;
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
public class RecipeService {
    @Autowired
    RecipeRepository repository;

    public List<Recipe> findAll(){
        return repository.findAll();
    }
    public  Recipe findById(UUID id){
        Optional<Recipe> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ResourceNotFoundException(id));
    }
    public  Recipe insert(Recipe obj){
        return  repository.save(obj);
    }
    public void delete(UUID id){
        try{
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
    public Recipe update(UUID id, Recipe recipe){
        try{
            Recipe entity = repository.getReferenceById(id);
            updateRecipe(entity, recipe);
            return repository.save(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void  updateRecipe(Recipe entity, Recipe obj){
        entity.setTitle(obj.getTitle());
        entity.setDescription(obj.getDescription());
        entity.setImageUrl(obj.getImageUrl());
        entity.setInstructions(obj.getInstructions());
        entity.setPrepTime(obj.getPrepTime());
        entity.setCategories(obj.getCategories());
        entity.setDifficulty(obj.getDifficulty());
        entity.setRecipeIngredients(obj.getRecipeIngredients());
    }
}
