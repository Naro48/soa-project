package com.example.transport.controller;

import com.example.transport.model.Horaire;
import com.example.transport.service.HoraireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horaires")
public class HoraireController {
    @Autowired
    private HoraireService horaireService;

    // Récupérer tous les horaires
    @GetMapping
    public List<Horaire> getAllHoraires() {
        return horaireService.getAllHoraires();
    }

    // Récupérer les horaires d'un trajet spécifique
    @GetMapping("/trajet/{id}")
    public List<Horaire> getHorairesByTrajet(@PathVariable Long id) {
        return horaireService.getHorairesByTrajet(id);
    }

    // Ajouter un nouvel horaire
    @PostMapping
    public Horaire createHoraire(@RequestBody Horaire horaire) {
        return horaireService.addHoraire(horaire);
    }

    // Mettre à jour un horaire existant
    @PutMapping("/{id}")
    public Horaire updateHoraire(@PathVariable Long id, @RequestBody Horaire horaireDetails) {
        return horaireService.updateHoraire(id, horaireDetails);
    }

    // Supprimer un horaire
    @DeleteMapping("/{id}")
    public void deleteHoraire(@PathVariable Long id) {
        horaireService.deleteHoraire(id);
    }
}
