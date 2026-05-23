package com.desafio_fullstack.projedata.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio_fullstack.projedata.domain.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    public boolean existsByName(String name);
}
