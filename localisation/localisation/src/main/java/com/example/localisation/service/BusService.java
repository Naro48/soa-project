package com.example.localisation.service;

import com.example.localisation.DTO.BusDTO;
import com.example.localisation.DTO.BusLocationDTO;
import com.example.localisation.DTO.TrajetDTO;
import com.example.localisation.model.Bus;
import com.example.localisation.model.BusLocation;
import com.example.localisation.repository.BusRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusService {

    private final BusRepository busRepository;

    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public List<BusDTO> getAllBuses() {
        return busRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public BusDTO getBusById(Long id) {
        Bus bus = busRepository.findById(id).orElseThrow(() -> new RuntimeException("Bus not found"));
        return convertToDTO(bus);
    }

    public Bus saveBus(Bus bus) {
        return busRepository.save(bus);
    }

    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }

    public BusDTO updateBusPosition(Long id) {
        Bus bus = busRepository.findById(id).orElseThrow(() -> new RuntimeException("Bus not found"));

        if (bus.getTrajet() == null || bus.getTrajet().getLocations().isEmpty()) {
            throw new RuntimeException("No locations available for the associated trajet");
        }

        List<BusLocation> locations = bus.getTrajet().getLocations();
        Integer currentIndex = bus.getCurrentLocationIndex();

        if (currentIndex == null || currentIndex >= locations.size()) {
            // Reset to the start if we've reached the end of the trajet
            currentIndex = 0;
        }

        // Update the position
        BusLocation currentLocation = locations.get(currentIndex);
        bus.setCurrentLatitude(currentLocation.getLatitude());
        bus.setCurrentLongitude(currentLocation.getLongitude());
        bus.setCurrentLocationIndex(currentIndex + 1); // Move to the next location

        busRepository.save(bus);
        return convertToDTO(bus);
    }

    // Scheduled method to update bus positions in real-time
    @Scheduled(fixedRate = 5000) // Runs every 5 seconds
    public void updateAllBusPositions() {
        List<Bus> buses = busRepository.findAll();
        for (Bus bus : buses) {
            if (bus.getTrajet() != null && !bus.getTrajet().getLocations().isEmpty()) {
                updateBusPosition(bus.getId());
            }
        }
    }

    // Convert Bus entity to BusDTO
    private BusDTO convertToDTO(Bus bus) {
        BusDTO dto = new BusDTO();
        dto.setId(bus.getId());
        dto.setName(bus.getName());
        dto.setCurrentLatitude(bus.getCurrentLatitude());
        dto.setCurrentLongitude(bus.getCurrentLongitude());

        if (bus.getTrajet() != null) {
            TrajetDTO trajetDTO = new TrajetDTO();
            trajetDTO.setId(bus.getTrajet().getId());
            trajetDTO.setName(bus.getTrajet().getName());
            trajetDTO.setLocations(bus.getTrajet().getLocations().stream().map(location -> {
                BusLocationDTO locationDTO = new BusLocationDTO();
                locationDTO.setId(location.getId());
                locationDTO.setLatitude(location.getLatitude());
                locationDTO.setLongitude(location.getLongitude());
                return locationDTO;
            }).collect(Collectors.toList()));
            dto.setTrajet(trajetDTO);
        }

        return dto;
    }
}
