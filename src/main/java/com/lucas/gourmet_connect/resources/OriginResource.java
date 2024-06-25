package com.lucas.gourmet_connect.resources;

import com.lucas.gourmet_connect.domain.Ingredient;
import com.lucas.gourmet_connect.domain.Origin;
import com.lucas.gourmet_connect.services.IngredientService;
import com.lucas.gourmet_connect.services.OriginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/origins")
public class OriginResource {
    @Autowired
    OriginServices service;

    @GetMapping
    public ResponseEntity<List<Origin>> findAll(){
        List<Origin> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Origin> findById(@PathVariable UUID id){
        Origin obj = service.findById(id);
        return  ResponseEntity.ok().body(obj);
    }
}
