package com.desafio_fullstack.projedata.infrastructure.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.desafio_fullstack.projedata.domain.entities.Person;
import com.desafio_fullstack.projedata.domain.repositories.PersonRepository;
import com.desafio_fullstack.projedata.infrastructure.dto.requests.PersonRequest;
import com.desafio_fullstack.projedata.infrastructure.dto.response.PersonResponse;

@Service
public class PersonService {
    PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public PersonResponse findById(Long id) {
        return PersonResponse.fromEntity(
                repository.findById(id).orElseThrow(() -> new RuntimeException("Person with id " + id + " not found")));
    }

    public List<PersonResponse> findAll(Pageable page) {
        return repository.findAll(page).stream().map(p -> PersonResponse.fromEntity(p)).toList();
    }

    public PersonResponse update(Long id, PersonRequest person) {
        Person existedPerson = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Person with id " + id + " not found"));
        if (!repository.existsByName(person.name())) {
            throw new RuntimeException("Person with name " + person.name() + " already exists");
        }
        existedPerson.setName(person.name());
        return PersonResponse.fromEntity(repository.save(existedPerson));
    }

    public PersonResponse save(PersonRequest person) {
        if (repository.existsByName(person.name())) {
            throw new RuntimeException("Person with name " + person.name() + " already exists");
        }
        return PersonResponse.fromEntity(repository.save(person.toEntity()));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Person with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}
