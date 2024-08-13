package com.lucas.gourmet_connect.services;

import com.lucas.gourmet_connect.entities.Ingredient;
import com.lucas.gourmet_connect.dto.IngredientDTO;
import com.lucas.gourmet_connect.entities.IngredientCategory;
import com.lucas.gourmet_connect.mapper.IngredientMapper;
import com.lucas.gourmet_connect.repositories.IngredientCategoryRepository;
import com.lucas.gourmet_connect.repositories.IngredientRepository;
import com.lucas.gourmet_connect.repositories.OriginRepository;
import com.lucas.gourmet_connect.services.exceptions.DatabaseException;
import com.lucas.gourmet_connect.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
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
public class IngredientService {
    @Autowired
    private IngredientRepository repository;
    @Autowired
    private OriginRepository originRepository;
    @Autowired
    private IngredientCategoryRepository categoryRepository;

    public List<IngredientDTO> findAll() {
        List <Ingredient> list = repository.findAll();
        return list.stream().map(IngredientMapper::toDTO).collect(Collectors.toList());
    }

    public IngredientDTO findById(UUID id) {
        Optional<Ingredient> obj = repository.findById(id);
        Ingredient ingredient = obj.orElseThrow(() -> new ResourceNotFoundException(id));
        return IngredientMapper.toDTO(ingredient);
    }
    public Ingredient findById2(UUID id) {
        Optional<Ingredient> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public IngredientDTO insert(IngredientDTO ingredientDTO) {
        Ingredient ingredient = IngredientMapper.toEntity(ingredientDTO);
        Ingredient savedIngredient = repository.save(ingredient);
        return IngredientMapper.toDTO(savedIngredient);
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

    public IngredientDTO update(UUID id, IngredientDTO ingredient) {
        try {
            Ingredient entity = repository.getReferenceById(id);
            updateIngredient(entity, ingredient);
            return IngredientMapper.toDTO(repository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateIngredient(Ingredient entity, IngredientDTO dto) {

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setUnit(dto.getUnit());
        entity.setImageUrl(dto.getImageUrl());
        if (dto.getOrigin() != null) {
            entity.setOrigin(originRepository.findById(dto.getOrigin().getId())
                    .orElseThrow(() -> new ResourceNotFoundException(dto.getOrigin().getId())));
        }

        if (dto.getCategories() != null) {
            Set<IngredientCategory> categories = dto.getCategories().stream()
                    .map(categoryDTO -> categoryRepository.findById(categoryDTO.getId())
                            .orElseThrow(() -> new ResourceNotFoundException(categoryDTO.getId())))
                    .collect(Collectors.toSet());
            entity.getCategories().clear();
            entity.getCategories().addAll(categories);
        }

        if (dto.getSubstitutes() != null) {
            Set<Ingredient> substitutes = dto.getSubstitutes().stream()
                    .map(substituteDTO -> repository.findById(substituteDTO.getId())
                            .orElseThrow(() -> new ResourceNotFoundException(substituteDTO.getId())))
                    .collect(Collectors.toSet());

            entity.getSubstitutes().clear();
            entity.getSubstitutes().addAll(substitutes);
        }
    }
}

