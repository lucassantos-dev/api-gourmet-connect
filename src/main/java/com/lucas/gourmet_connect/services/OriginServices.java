package com.lucas.gourmet_connect.services;


import com.lucas.gourmet_connect.domain.Origin;

import com.lucas.gourmet_connect.repositories.OriginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OriginServices {

    @Autowired
    OriginRepository repository;

    public List<Origin> findAll(){
        return repository.findAll();
    }
    public  Origin findById(UUID id){
        Optional<Origin> obj = repository.findById(id);
        return obj.get();
    }
}
