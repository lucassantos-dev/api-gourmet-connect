package com.lucas.gourmet_connect.services;


import com.lucas.gourmet_connect.domain.Recipe;
import com.lucas.gourmet_connect.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return obj.get();
    }

}
