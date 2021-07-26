package com.whiteboard.angularstarter.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/objects", produces = "application/json")
public class ObjectController {

    private final ObjectService objectService;

    @Autowired
    public ObjectController(ObjectService objectService) {
        this.objectService = objectService;
    }

    @GetMapping()
    public ResponseEntity<List<GenericObject>> getAllObjects() {
        return ResponseEntity.ok(objectService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericObject> getObjectById(@PathVariable("id") Long id) {
        Optional<GenericObject> genericObject = objectService.findById(id);

        return genericObject.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<GenericObject> createObject(@RequestBody GenericObject genericObject) {
        GenericObject savedObject = objectService.save(genericObject);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedObject.getId()).toUri();

        return ResponseEntity.created(location).body(savedObject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericObject> updateObject(@RequestBody GenericObject genericObject, @PathVariable Long id) {
        Optional<GenericObject> optionalObject = objectService.findById(id);

        if (!optionalObject.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        genericObject.setId(id);
        genericObject = objectService.save(genericObject);

        return ResponseEntity.ok(genericObject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteObject(@PathVariable Long id) {
        objectService.delete(id);
        return ResponseEntity.ok().build();
    }

}
