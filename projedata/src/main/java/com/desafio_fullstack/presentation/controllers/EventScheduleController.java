package com.desafio_fullstack.presentation.controllers;

import java.util.List;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafio_fullstack.projedata.infrastructure.dto.requests.EventScheduleRequest;
import com.desafio_fullstack.projedata.infrastructure.dto.response.EventScheduleResponse;
import com.desafio_fullstack.projedata.infrastructure.services.EventScheduleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("events")
public class EventScheduleController {

    Logger logger = LoggerFactory.getLogger(EventScheduleController.class);
    private final EventScheduleService eventScheduleService;

    public EventScheduleController(EventScheduleService eventScheduleService) {
        this.eventScheduleService = eventScheduleService;
    }

    @GetMapping("{id}")
    public ResponseEntity<EventScheduleResponse> get(@PathVariable Long id) {
        var response = EventScheduleResponse.fromEntity(eventScheduleService.findById(id));
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EventScheduleResponse>> getAll(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        PageRequest pageable = PageRequest.of(page, size);
        List<EventScheduleResponse> response = eventScheduleService.findAll(pageable);
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping
    public ResponseEntity<EventScheduleResponse> post(@RequestBody @Valid EventScheduleRequest request) {
        logger.info("Creating new event schedule");
        return ResponseEntity.status(201).body(eventScheduleService.save(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<EventScheduleResponse> put(@PathVariable Long id,
            @RequestBody @Valid EventScheduleRequest request) {
        logger.info("Updating event schedule with ID: {}", id);
        return ResponseEntity.status(200).body(eventScheduleService.update(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        logger.info("Deleting event schedule with ID: {}", id);
        eventScheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
