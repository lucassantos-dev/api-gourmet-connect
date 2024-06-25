package com.lucas.gourmet_connect.resources;
import com.lucas.gourmet_connect.domain.IngredientCategory;
import com.lucas.gourmet_connect.services.IngredientCategoriesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ingredient_categories")
public class IngredientCategoriesResource {
    @Autowired
    IngredientCategoriesService service;

    @GetMapping
    public ResponseEntity<List<IngredientCategory>> findAll(){
        List<IngredientCategory> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<IngredientCategory> findById(@PathVariable UUID id){
        IngredientCategory obj = service.findById(id);
        return  ResponseEntity.ok().body(obj);
    }

}
