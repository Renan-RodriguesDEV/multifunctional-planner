package com.desafio_fullstack.projedata.domain.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.desafio_fullstack.projedata.domain.entities.EventSchedule;

public interface EventScheduleRepository extends JpaRepository<EventSchedule, Long> {

    @Query(value = """
            SELECT * FROM tb_event_schedule WHERE scheduled_at BETWEEN :scheduledAt AND :scheduledAt + INTERVAL(30 MINUTE)
            """, nativeQuery = true)
    public Optional<List<EventSchedule>> findByScheduledAtIn(LocalDateTime scheduledAt);
}
