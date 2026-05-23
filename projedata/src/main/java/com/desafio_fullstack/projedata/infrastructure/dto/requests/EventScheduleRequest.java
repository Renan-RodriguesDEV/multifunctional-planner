package com.desafio_fullstack.projedata.infrastructure.dto.requests;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.desafio_fullstack.projedata.domain.entities.EventSchedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record EventScheduleRequest(@NotBlank(message = "O nome é obrigatório") String name, String description,
        @Positive(message = "O preço deve ser um valor positivo") BigDecimal price,
        @Positive(message = "O estoque deve ser um valor positivo") Long stock,
        LocalDateTime scheduledAt,
        PersonRequest person) {

    public EventSchedule toEntity() {
        return new EventSchedule(name, description, price, stock, scheduledAt, person.toEntity());
    }
}
