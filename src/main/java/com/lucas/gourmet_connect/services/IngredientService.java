package com.lucas.gourmet_connect.services;

import com.lucas.gourmet_connect.domain.Ingredient;
import com.lucas.gourmet_connect.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IngredientService {

@Autowired
IngredientRepository repository;

public List<Ingredient> findAll(){
    return repository.findAll();
    }
public  Ingredient findById(UUID id){
    Optional<Ingredient> obj = repository.findById(id);
    return obj.get();
    }
}

