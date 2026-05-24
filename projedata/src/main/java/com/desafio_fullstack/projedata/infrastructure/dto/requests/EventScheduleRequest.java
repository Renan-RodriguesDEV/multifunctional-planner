package com.desafio_fullstack.projedata.infrastructure.dto.requests;

import java.time.LocalDateTime;

import com.desafio_fullstack.projedata.domain.entities.EventSchedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EventScheduleRequest(@NotBlank(message = "O nome é obrigatório") String name, String description,
        @Positive(message = "O preço deve ser um valor positivo") @Positive(message = "O estoque deve ser um valor positivo") Long count,
        @NotNull(message = "A data e hora do evento são obrigatórias") LocalDateTime scheduledAt,
        @NotNull(message = "O ID do responsável é obrigatório") Long person_id,
        @NotNull(message = "A localização é obrigatória") Long location_id) {

    public EventSchedule toEntity() {
        return new EventSchedule(name, description, count, scheduledAt);
    }
}
