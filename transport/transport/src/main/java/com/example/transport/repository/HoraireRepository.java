package com.example.transport.repository;

import com.example.transport.model.Horaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoraireRepository extends JpaRepository<Horaire, Long> {
    List<Horaire> findByTrajetId(Long trajetId);
}
