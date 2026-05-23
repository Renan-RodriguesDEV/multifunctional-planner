package com.desafio_fullstack.projedata.infrastructure.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.desafio_fullstack.projedata.domain.entities.EventSchedule;

public record EventScheduleResponse(Long id, String name, String description, BigDecimal price, Long stock,
        PersonResponse person, LocalDateTime scheduledAt, LocalDateTime createdAt, LocalDateTime updatedAt) {

    public EventSchedule toEntity() {
        return new EventSchedule(id, name, description, price, stock, person.toEntity(), true, scheduledAt, createdAt,
                updatedAt);
    }

    public static EventScheduleResponse fromEntity(EventSchedule event) {
        return new EventScheduleResponse(
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getPrice(),
                event.getStock(),
                PersonResponse.fromEntity(event.getPerson()),
                event.getScheduledAt(),
                event.getCreatedAt(),
                event.getUpdatedAt());
    }
}
