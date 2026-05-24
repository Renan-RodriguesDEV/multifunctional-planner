package com.desafio_fullstack.projedata.infrastructure.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.desafio_fullstack.projedata.domain.entities.Person;

public record PersonResponse(Long id, String name, List<EventScheduleResponse> eventSchedules,
        LocalDateTime createdAt) {
    public Person toEntity() {
        List<EventScheduleResponse> schedules = eventSchedules == null ? List.of() : eventSchedules;
        return new Person(id, name, schedules.stream().map(e -> e.toEntity()).toList(), createdAt);
    }

    public static PersonResponse fromEntity(Person person) {
        List<EventScheduleResponse> schedules = person.getEventSchedules() == null ? List.of()
                : person.getEventSchedules().stream().map(e -> EventScheduleResponse.fromEntity(e)).toList();
        return new PersonResponse(person.getId(), person.getName(),
                schedules, person.getCreatedAt());
    }
}
