package com.desafio_fullstack.projedata.infrastructure.dto.requests;

import com.desafio_fullstack.projedata.domain.entities.Person;

public record PersonRequest(String name) {
    public Person toEntity() {
        return new Person(name);
    }
}
