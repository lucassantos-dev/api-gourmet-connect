package com.lucas.gourmet_connect.resources;

import com.lucas.gourmet_connect.domain.RecipeCategory;
import com.lucas.gourmet_connect.services.RecipeCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/recipe_categories")
public class RecipeCategoryResource {
    @Autowired
    RecipeCategoriesService service;

    @GetMapping
    public ResponseEntity<List<RecipeCategory>> findAll(){
        List<RecipeCategory> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<RecipeCategory> findById(@PathVariable UUID id){
        RecipeCategory obj = service.findById(id);
        return  ResponseEntity.ok().body(obj);
    }
}
