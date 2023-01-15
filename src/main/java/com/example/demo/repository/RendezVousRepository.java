package com.example.demo.repository;
import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Medecin;
import com.example.demo.model.RendezVous;

@Repository
public interface RendezVousRepository extends CrudRepository<RendezVous, Long> {
    Page<RendezVous> findByDate(Date date, Pageable pageable);
//    Page<RendezVous> findAll(PageRequest p);
    List<RendezVous >  findByMedecin(Medecin m);
    
}