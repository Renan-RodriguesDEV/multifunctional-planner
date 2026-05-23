package com.desafio_fullstack.presentation.controllers;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio_fullstack.projedata.infrastructure.dto.requests.PersonRequest;
import com.desafio_fullstack.projedata.infrastructure.dto.response.PersonResponse;
import com.desafio_fullstack.projedata.infrastructure.services.PersonService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(personService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<PersonResponse>> getAll(@PathParam("page") int page, @PathParam("size") int size) {
        return ResponseEntity.ok(personService.findAll(PageRequest.of(page, size)));
    }

    @PostMapping("/")
    public ResponseEntity<PersonResponse> create(@RequestBody PersonRequest request) {
        return ResponseEntity.status(201).body(personService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> put(@PathVariable Long id, @RequestBody PersonRequest request) {
        return ResponseEntity.status(201).body(personService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
