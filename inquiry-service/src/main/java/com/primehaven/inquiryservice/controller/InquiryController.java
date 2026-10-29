package com.primehaven.inquiryservice.controller;

import com.primehaven.inquiryservice.entity.Inquiry;
import com.primehaven.inquiryservice.repository.InquiryRepository;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inquiries")
public class InquiryController {

    private final InquiryRepository repository;

    public InquiryController(InquiryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/public/info")
    public ResponseEntity<String> publicInfo() {
        return ResponseEntity.ok("Public inquiry-service info - no authentication required.");
    }

    @PostMapping
    public ResponseEntity<Inquiry> create(@RequestBody Inquiry i) {
        return ResponseEntity.status(201).body(repository.save(i));
    }

    @GetMapping
    public List<Inquiry> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inquiry> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/me")
    public ResponseEntity<String> whoAmI(Authentication auth) {
        return ResponseEntity.ok(
                "Authenticated as '" + auth.getName() + "' with role(s) " + auth.getAuthorities());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inquiry> update(@PathVariable Long id, @RequestBody Inquiry item) {
        return repository.findById(id)
                .map(existing -> {
                    if (item.getPropertyId() != null) {
                        existing.setPropertyId(item.getPropertyId());
                    }
                    if (item.getUserId() != null) {
                        existing.setUserId(item.getUserId());
                    }
                    if (item.getMessage() != null) {
                        existing.setMessage(item.getMessage());
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
