package com.example.transport.controller;

import com.example.transport.model.Trajet;
import com.example.transport.service.TrajetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trajets")
public class TrajetController {
    @Autowired
    private TrajetService trajetService;

    @GetMapping
    public List<Trajet> getAllTrajets() {
        return trajetService.getAllTrajets();
    }

    @PostMapping
    public Trajet createTrajet(@RequestBody Trajet trajet) {
        return trajetService.addTrajet(trajet);
    }

    @PutMapping("/{id}")
    public Trajet updateTrajet(@PathVariable Long id, @RequestBody Trajet trajet) {
        return trajetService.updateTrajet(id, trajet);
    }

    @DeleteMapping("/{id}")
    public void deleteTrajet(@PathVariable Long id) {
        trajetService.deleteTrajet(id);
    }
}
