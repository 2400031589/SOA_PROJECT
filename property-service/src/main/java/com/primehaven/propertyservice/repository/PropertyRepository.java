package com.primehaven.propertyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.primehaven.propertyservice.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
