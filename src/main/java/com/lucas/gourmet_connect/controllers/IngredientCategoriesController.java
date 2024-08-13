package com.lucas.gourmet_connect.controllers;
import com.lucas.gourmet_connect.entities.IngredientCategory;
import com.lucas.gourmet_connect.services.IngredientCategoriesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ingredient_categories")
public class IngredientCategoriesController {
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
    @PostMapping
    public  ResponseEntity<IngredientCategory> insert(@RequestBody IngredientCategory obj){
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
    public ResponseEntity<IngredientCategory> update(@PathVariable UUID id, @RequestBody IngredientCategory obj){
        obj = service.update(id, obj);
        return  ResponseEntity.ok().body(obj);
    }
}
