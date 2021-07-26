package com.whiteboard.angularstarter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObjectService {
    private final ObjectRepository repository;

    @Autowired
    public ObjectService(ObjectRepository repository) {
        this.repository = repository;
    }

    public List<GenericObject> findAll() {
        return repository.findAll();
    }

    public Optional<GenericObject> findById(Long id) {
        return repository.findById(id);
    }

    public GenericObject save(GenericObject genericObject) {
        return repository.save(genericObject);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
