package com.lucas.gourmet_connect.resources;
import com.lucas.gourmet_connect.dto.RecipeDTO;
import com.lucas.gourmet_connect.entities.Recipe;
import com.lucas.gourmet_connect.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/recipes")
public class RecipeResource {
    @Autowired
    RecipeService service;

    @GetMapping
    public ResponseEntity<List<RecipeDTO>> findAll(){
        List<RecipeDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<RecipeDTO> findById(@PathVariable UUID id){
        RecipeDTO obj = service.findById(id);
        return  ResponseEntity.ok().body(obj);
    }
    @PostMapping
    public  ResponseEntity<Recipe> insert(@RequestBody Recipe obj){
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
    public ResponseEntity<Recipe> update(@PathVariable UUID id, @RequestBody Recipe obj){
        obj = service.update(id, obj);
        return  ResponseEntity.ok().body(obj);
    }
}
