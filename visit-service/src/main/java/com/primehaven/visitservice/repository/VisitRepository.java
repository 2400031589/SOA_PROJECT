
package com.primehaven.visitservice.repository;

import com.primehaven.visitservice.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    List<Visit> findByUserId(Long userId);

    Optional<Visit> findByVisitIdAndUserId(Long visitId, Long userId);
}
