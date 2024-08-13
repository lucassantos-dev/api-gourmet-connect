package com.lucas.gourmet_connect.services;
import com.lucas.gourmet_connect.dto.RecipeDTO;
import com.lucas.gourmet_connect.entities.Ingredient;
import com.lucas.gourmet_connect.entities.Recipe;
import com.lucas.gourmet_connect.entities.RecipeCategory;
import com.lucas.gourmet_connect.entities.RecipeIngredients;
import com.lucas.gourmet_connect.mapper.RecipeMapper;
import com.lucas.gourmet_connect.repositories.DifficultyRepository;
import com.lucas.gourmet_connect.repositories.OriginRepository;
import com.lucas.gourmet_connect.repositories.RecipeCategoryRepository;
import com.lucas.gourmet_connect.repositories.RecipeRepository;
import com.lucas.gourmet_connect.services.exceptions.DatabaseException;
import com.lucas.gourmet_connect.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository repository;
    @Autowired
    RecipeMapper mapper;
    @Autowired
    OriginRepository originRepository;
    @Autowired
    DifficultyRepository difficultyRepository;
    @Autowired
    RecipeCategoryRepository categoryRepository;
    @Autowired
    IngredientService ingredientService;
    public List<RecipeDTO> findAll(){
        List<Recipe> list = repository.findAll();
        return list.stream().map(RecipeMapper::toDTO).collect(Collectors.toList());
    }

    public  RecipeDTO findById(UUID id){
        Optional<Recipe> obj = repository.findById(id);
        Recipe recipe = obj.orElseThrow(()-> new ResourceNotFoundException(id));
        return RecipeMapper.toDTO(recipe);

    }
    public  Recipe findById2(UUID id){
        Optional<Recipe> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ResourceNotFoundException(id));

    }
    public  RecipeDTO insert(RecipeDTO obj){
        Recipe recipe = mapper.toEntity(obj);
        recipe = repository.save(recipe);
        return RecipeMapper.toDTO(recipe);
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
    @Transactional
    public RecipeDTO update(UUID id, RecipeDTO recipe){
        try{
            Recipe entity = repository.getReferenceById(id);
            updateRecipe(entity, recipe);
            return RecipeMapper.toDTO(repository.save(entity));
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void  updateRecipe(Recipe entity, RecipeDTO dto){
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setImageUrl(dto.getImageUrl());
        entity.setInstructions(dto.getInstructions());
        entity.setPrepTime(dto.getPrepTime());

        if (dto.getOrigin() != null) {
            entity.setOrigin(originRepository.findById(dto.getOrigin().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(dto.getOrigin().getId())));
        }

        if (dto.getDifficulty() != null) {
            entity.setDifficulty(difficultyRepository.findById(dto.getDifficulty().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(dto.getDifficulty().getId())));
        }

        if (dto.getCategories() != null) {
            Set<RecipeCategory> categories = dto.getCategories().stream()
                    .map(categoryDTO -> categoryRepository.findById(categoryDTO.getId())
                            .orElseThrow(() -> new ResourceNotFoundException(categoryDTO.getId())))
                    .collect(Collectors.toSet());

            entity.getCategories().clear();
            entity.getCategories().addAll(categories);

        if (dto.getIngredients() != null) {
            Set<RecipeIngredients> ingredients = dto.getIngredients().stream()
                    .map(ingredientDTO -> {
                        RecipeIngredients recipeIngredient = new RecipeIngredients();
                        recipeIngredient.setQuantity(ingredientDTO.getQuantity());
                        Ingredient ingredient = ingredientService.findById2(ingredientDTO.getIngredient().getId());
                        recipeIngredient.setIngredient(ingredient);
                        recipeIngredient.setRecipe(entity);
                        return recipeIngredient;
                    })
                    .collect(Collectors.toSet());
            entity.getRecipeIngredients().clear();
            entity.getRecipeIngredients().addAll(ingredients);
        }
    }
    }
}
