package com.lucas.gourmet_connect.services;
import com.lucas.gourmet_connect.domain.RecipeCategory;
import com.lucas.gourmet_connect.repositories.RecipeCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RecipeCategoriesService {

    @Autowired
    RecipeCategoryRepository repository;

    public List<RecipeCategory> findAll(){
        return repository.findAll();
    }
    public  RecipeCategory findById(UUID id){
        Optional<RecipeCategory> obj = repository.findById(id);
        return obj.get();
    }
}
