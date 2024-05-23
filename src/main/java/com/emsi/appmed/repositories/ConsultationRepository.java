package com.emsi.appmed.repositories;

import com.emsi.appmed.entities.Consultation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

@Transactional
public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {
//    Page<Consultation> findByDate(Timestamp kw, PageRequest of);
//
//    Page<Consultation> findByDateAndMedecinIdIn(Timestamp kw, List<Integer> medecinIds, Pageable pageable);
    //Page<Consultation> findByMedecinId(Integer medecinId, PageRequest of);

    Page<Consultation> findByMedcinsId(Integer medecinId, Pageable pageable);

    Page<Consultation> findByPatientsId(Integer patientId, PageRequest of);

    Page<Consultation> findByPatientsIdIn(List<Integer> patientIds, PageRequest of);
}

