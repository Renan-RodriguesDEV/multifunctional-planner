package com.desafio_fullstack.projedata.infrastructure.dto.response;

import java.time.LocalDateTime;

import com.desafio_fullstack.projedata.domain.entities.EventSchedule;

public record EventScheduleResponse(Long id, String name, String description, Long count,
        Long person_id, LocationResponse location, LocalDateTime scheduledAt, LocalDateTime createdAt,
        LocalDateTime updatedAt) {

    public EventSchedule toEntity() {
        return new EventSchedule(name, description, count,
                scheduledAt);
    }

    public static EventScheduleResponse fromEntity(EventSchedule event) {
        return new EventScheduleResponse(
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getCount(),
                event.getPerson() != null ? event.getPerson().getId() : null,
                LocationResponse.fromEntity(event.getLocation()),
                event.getScheduledAt(),
                event.getCreatedAt(),
                event.getUpdatedAt());
    }
}
