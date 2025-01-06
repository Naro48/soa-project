package com.example.localisation.controller;

import com.example.localisation.DTO.BusDTO;
import com.example.localisation.model.Bus;
import com.example.localisation.service.BusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping
    public ResponseEntity<List<BusDTO>> getAllBuses() {
        return ResponseEntity.ok(busService.getAllBuses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusDTO> getBusById(@PathVariable Long id) {
        return ResponseEntity.ok(busService.getBusById(id));
    }

    @PostMapping
    public ResponseEntity<Bus> saveBus(@RequestBody Bus bus) {
        return ResponseEntity.ok(busService.saveBus(bus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Long id) {
        busService.deleteBus(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/update-position")
    public ResponseEntity<BusDTO> updateBusPosition(@PathVariable Long id) {
        BusDTO updatedBus = busService.updateBusPosition(id);
        return ResponseEntity.ok(updatedBus);
    }
}
