package com.lucas.gourmet_connect.resources;

import com.lucas.gourmet_connect.domain.Ingredient;
import com.lucas.gourmet_connect.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ingredients")
public class IngredientResource {

    @Autowired
    IngredientService service;

    @GetMapping
    public ResponseEntity<List<Ingredient>> findAll(){
        List<Ingredient> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Ingredient> findById(@PathVariable UUID id){
        Ingredient obj = service.findById(id);
        return  ResponseEntity.ok().body(obj);
    }
}

