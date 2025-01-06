package com.example.transport.repository;
import com.example.transport.repository.TrajetRepository;

import com.example.transport.model.Trajet;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.transport.repository.TrajetRepository;


public interface TrajetRepository extends JpaRepository<Trajet, Long> {}
