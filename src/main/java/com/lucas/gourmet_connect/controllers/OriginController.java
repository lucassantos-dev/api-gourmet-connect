package com.lucas.gourmet_connect.controllers;
import com.lucas.gourmet_connect.entities.Origin;
import com.lucas.gourmet_connect.services.OriginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/origins")
public class OriginController {
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
    @PostMapping
    public  ResponseEntity<Origin> insert(@RequestBody Origin obj){
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
    public ResponseEntity<Origin> update(@PathVariable UUID id, @RequestBody Origin obj){
        obj = service.update(id, obj);
        return  ResponseEntity.ok().body(obj);
    }
}
