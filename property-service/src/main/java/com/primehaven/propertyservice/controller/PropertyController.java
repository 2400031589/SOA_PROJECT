package com.primehaven.propertyservice.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.primehaven.propertyservice.entity.Property;
import com.primehaven.propertyservice.repository.PropertyRepository;

@RestController
@RequestMapping("/properties")
public class PropertyController {
    private final PropertyRepository repository;

    public PropertyController(PropertyRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Property create(@RequestBody Property item) {
        return repository.save(item);
    }

    @GetMapping
    public List<Property> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getById(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Property> update(@PathVariable Long id, @RequestBody Property item) {
        return repository.findById(id).map(existing -> {
            if (item.getPropertyName() != null) {
                existing.setPropertyName(item.getPropertyName());
            }
            if (item.getLocation() != null) {
                existing.setLocation(item.getLocation());
            }
            if (item.getPrice() != null) {
                existing.setPrice(item.getPrice());
            }
            if (item.getStatus() != null) {
                existing.setStatus(item.getStatus());
            }
            return ResponseEntity.ok(repository.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
