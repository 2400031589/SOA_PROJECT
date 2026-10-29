
package com.primehaven.visitservice.controller;

import com.primehaven.visitservice.entity.Visit;
import com.primehaven.visitservice.repository.VisitRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitController {

    private final VisitRepository repository;

    public VisitController(VisitRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Visit> schedule(@RequestBody Visit visit) {
        return ResponseEntity.status(201)
                .body(repository.save(visit));
    }

    @GetMapping
    public List<Visit> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visit> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Visit> update(
            @PathVariable Long id,
            @RequestBody Visit visit) {

        return repository.findById(id)
                .map(existing -> {
                    if (visit.getPropertyId() != null) {
                        existing.setPropertyId(visit.getPropertyId());
                    }

                    if (visit.getUserId() != null) {
                        existing.setUserId(visit.getUserId());
                    }

                    if (visit.getVisitDate() != null) {
                        existing.setVisitDate(visit.getVisitDate());
                    }

                    return ResponseEntity.ok(repository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
