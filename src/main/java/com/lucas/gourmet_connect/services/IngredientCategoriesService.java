package com.lucas.gourmet_connect.services;


import com.lucas.gourmet_connect.domain.IngredientCategory;
import com.lucas.gourmet_connect.repositories.IngredientCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IngredientCategoriesService {


    @Autowired
    IngredientCategoryRepository repository;

    public List<IngredientCategory> findAll(){
        return repository.findAll();
    }
    public  IngredientCategory findById(UUID id){
        Optional<IngredientCategory> obj = repository.findById(id);
        return obj.get();
    }
}
