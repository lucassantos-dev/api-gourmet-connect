package com.lucas.gourmet_connect.services;


import com.lucas.gourmet_connect.entities.Origin;

import com.lucas.gourmet_connect.repositories.OriginRepository;
import com.lucas.gourmet_connect.services.exceptions.DatabaseException;
import com.lucas.gourmet_connect.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OriginServices {
    @Autowired
    private OriginRepository repository;

    public List<Origin> findAll() {
        return repository.findAll();
    }

    public Origin findById(UUID id) {
        Optional<Origin> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Origin insert(Origin obj) {
        return repository.save(obj);
    }

    public void delete(UUID id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Origin update(UUID id, Origin origin) {
        try {
            Origin entity = repository.getReferenceById(id);
            updateOrigin(entity, origin);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateOrigin(Origin entity, Origin obj) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
    }
}
