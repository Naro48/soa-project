package com.example.transport.service;

import com.example.transport.model.Trajet;
import com.example.transport.repository.TrajetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrajetService {

    @Autowired
    private TrajetRepository trajetRepository; // Injection de la dépendance

    public List<Trajet> getAllTrajets() {
        return trajetRepository.findAll(); // Retourne tous les trajets
    }

    public Trajet addTrajet(Trajet trajet) {
        return trajetRepository.save(trajet); // Sauvegarde un nouveau trajet
    }

    public Trajet updateTrajet(Long id, Trajet trajetDetails) {
        Trajet trajet = trajetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trajet non trouvé")); // Gère l'absence d'ID

        trajet.setNom(trajetDetails.getNom());
        trajet.setDescription(trajetDetails.getDescription());
        return trajetRepository.save(trajet); // Sauvegarde les modifications
    }

    public void deleteTrajet(Long id) {
        trajetRepository.deleteById(id); // Supprime un trajet par ID
    }
}
