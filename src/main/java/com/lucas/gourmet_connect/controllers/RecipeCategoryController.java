package com.lucas.gourmet_connect.controllers;
import com.lucas.gourmet_connect.entities.RecipeCategory;
import com.lucas.gourmet_connect.services.RecipeCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/recipe_categories")
public class RecipeCategoryController {
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

    @PostMapping
    public  ResponseEntity<RecipeCategory> insert(@RequestBody RecipeCategory obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<RecipeCategory> update(@PathVariable UUID id, @RequestBody RecipeCategory obj){
        obj = service.update(id, obj);
        return  ResponseEntity.ok().body(obj);
    }
}
