package com.primehaven.inquiryservice.repository;

import com.primehaven.inquiryservice.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Standard CRUD repository for {@link Inquiry}.
 */
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    // Custom query methods (if any) can be added here.
}