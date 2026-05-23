package com.desafio_fullstack.projedata.infrastructure.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.desafio_fullstack.projedata.domain.entities.Person;

public record PersonResponse(Long id, String name, List<EventScheduleResponse> eventSchedules,
        LocalDateTime createdAt) {
    public Person toEntity() {
        ;
        return new Person(id, name, eventSchedules.stream().map(e -> e.toEntity()).toList(), createdAt);
    }

    public static PersonResponse fromEntity(Person person) {
        return new PersonResponse(person.getId(), person.getName(),
                person.getEventSchedules().stream().map(e -> EventScheduleResponse.fromEntity(e)).toList(),
                person.getCreatedAt());
    }
}
