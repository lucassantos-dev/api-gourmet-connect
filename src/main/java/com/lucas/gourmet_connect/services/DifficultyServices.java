package com.lucas.gourmet_connect.services;

import com.lucas.gourmet_connect.entities.Difficulty;
import com.lucas.gourmet_connect.repositories.DifficultyRepository;
import com.lucas.gourmet_connect.services.exceptions.DatabaseException;
import com.lucas.gourmet_connect.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DifficultyServices {
    @Autowired
    private DifficultyRepository repository;

    public List<Difficulty> findAll() {
        return repository.findAll();
    }

    public Difficulty findById(Integer id) {
        Optional<Difficulty> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Difficulty insert(Difficulty obj) {
        return repository.save(obj);
    }

    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Difficulty update(Integer id, Difficulty difficulty) {
        try {
            Difficulty entity = repository.getReferenceById(id);
            updateDifficulty(entity, difficulty);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateDifficulty(Difficulty entity, Difficulty obj) {
        entity.setName(obj.getName());

    }
}
