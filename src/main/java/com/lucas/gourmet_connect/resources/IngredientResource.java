package com.lucas.gourmet_connect.resources;

import com.lucas.gourmet_connect.entities.Ingredient;

import com.lucas.gourmet_connect.dto.IngredientDTO;
import com.lucas.gourmet_connect.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ingredients")
public class IngredientResource {

    @Autowired
    IngredientService service;

    @GetMapping
    public ResponseEntity<List<IngredientDTO>> findAll(){
        List<IngredientDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<IngredientDTO> findById(@PathVariable UUID id){
        IngredientDTO obj = service.findById(id);
        return  ResponseEntity.ok().body(obj);
    }
    @PostMapping
    public  ResponseEntity<Ingredient> insert(@RequestBody Ingredient obj){
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
    public ResponseEntity<Ingredient> update(@PathVariable UUID id, @RequestBody Ingredient obj){
        obj = service.update(id, obj);
        return  ResponseEntity.ok().body(obj);
    }
}

