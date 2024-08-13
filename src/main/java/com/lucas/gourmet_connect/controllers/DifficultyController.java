package com.lucas.gourmet_connect.controllers;

import com.lucas.gourmet_connect.entities.Difficulty;


import com.lucas.gourmet_connect.services.DifficultyServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/difficulties")
public class DifficultyController {

    @Autowired
    DifficultyServices service;

    @GetMapping
    public ResponseEntity<List<Difficulty>> findAll(){
        List<Difficulty> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Difficulty> findById(@PathVariable Integer id){
        Difficulty obj = service.findById(id);
        return  ResponseEntity.ok().body(obj);
    }
    @PostMapping
    public  ResponseEntity<Difficulty> insert(@RequestBody Difficulty obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Difficulty> update(@PathVariable Integer id, @RequestBody Difficulty obj){
        obj = service.update(id, obj);
        return  ResponseEntity.ok().body(obj);
    }
}
