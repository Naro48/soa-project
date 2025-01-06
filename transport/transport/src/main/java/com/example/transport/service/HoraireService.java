package com.example.transport.service;

import com.example.transport.model.Horaire;
import com.example.transport.repository.HoraireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoraireService {
    @Autowired
    private HoraireRepository horaireRepository;

    // Récupérer tous les horaires
    public List<Horaire> getAllHoraires() {
        return horaireRepository.findAll();
    }

    // Récupérer les horaires d'un trajet spécifique
    public List<Horaire> getHorairesByTrajet(Long trajetId) {
        return horaireRepository.findByTrajetId(trajetId);
    }

    // Ajouter un nouvel horaire
    public Horaire addHoraire(Horaire horaire) {
        return horaireRepository.save(horaire);
    }

    // Mettre à jour un horaire existant
    public Horaire updateHoraire(Long id, Horaire horaireDetails) {
        Horaire horaire = horaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Horaire non trouvé"));
        horaire.setHeureDepart(horaireDetails.getHeureDepart());
        horaire.setHeureArrivee(horaireDetails.getHeureArrivee());
        horaire.setJourSemaine(horaireDetails.getJourSemaine());
        horaire.setTrajet(horaireDetails.getTrajet());
        return horaireRepository.save(horaire);
    }

    // Supprimer un horaire
    public void deleteHoraire(Long id) {
        horaireRepository.deleteById(id);
    }
}
