package com.lucas.gourmet_connect.resources;


import com.lucas.gourmet_connect.domain.Recipe;
import com.lucas.gourmet_connect.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/recipes")
public class RecipeResource {
    @Autowired
    RecipeService service;

    @GetMapping
    public ResponseEntity<List<Recipe>> findAll(){
        List<Recipe> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Recipe> findById(@PathVariable UUID id){
        Recipe obj = service.findById(id);
        return  ResponseEntity.ok().body(obj);
    }
}
