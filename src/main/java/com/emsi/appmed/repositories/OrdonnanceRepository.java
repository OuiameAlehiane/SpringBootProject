package com.emsi.appmed.repositories;

import com.emsi.appmed.entities.Ordonnance;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface OrdonnanceRepository extends JpaRepository<Ordonnance,Integer> {


    boolean existsByConsultationId(int consultationId);
}
