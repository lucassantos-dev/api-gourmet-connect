package com.lucas.gourmet_connect.services;

import com.lucas.gourmet_connect.domain.Difficulty;
import com.lucas.gourmet_connect.repositories.DifficultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DifficultyServices {

    @Autowired
    DifficultyRepository repository;

    public List<Difficulty> findAll() {
        return repository.findAll();
    }

    public Difficulty findById(Integer id) {
        Optional<Difficulty> obj = repository.findById(id);
        return obj.get();
    }
}
