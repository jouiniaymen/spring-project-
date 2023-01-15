package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.demo.model.Medecin;

@Repository
public interface MedecinRepository extends CrudRepository<Medecin, Long> {

    Page<Medecin> findByNomContains(String nom, Pageable pageable);
  List<  Medecin> findByspecialite(String a);

}