package com.desafio_fullstack.presentation.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio_fullstack.projedata.domain.exceptions.NotFoundException;
import com.desafio_fullstack.projedata.domain.repositories.LocationRepository;
import com.desafio_fullstack.projedata.infrastructure.dto.requests.LocationRequest;
import com.desafio_fullstack.projedata.infrastructure.dto.response.LocationResponse;

@RestController
@RequestMapping("locations")
public class LocationController {
    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @PostMapping
    public ResponseEntity<LocationResponse> create(@RequestBody LocationRequest request) {
        LocationResponse response = LocationResponse.fromEntity(locationRepository.save(request.toEntity()));
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<LocationResponse>> getAll() {
        List<LocationResponse> response = locationRepository.findAll().stream().map(l -> LocationResponse.fromEntity(l))
                .toList();
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationResponse> get(@PathVariable Long id) {
        LocationResponse response = LocationResponse.fromEntity(
                locationRepository.findById(id).orElseThrow(() -> new NotFoundException("Location not found")));

        return ResponseEntity.status(200).body(response);
    }
}
