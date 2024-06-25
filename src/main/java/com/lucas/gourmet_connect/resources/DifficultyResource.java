package com.lucas.gourmet_connect.resources;

import com.lucas.gourmet_connect.domain.Difficulty;

import com.lucas.gourmet_connect.services.DifficultyServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/difficulties")
public class DifficultyResource {


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
}
