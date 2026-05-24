package com.desafio_fullstack.projedata.infrastructure.services;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.desafio_fullstack.presentation.controllers.EventScheduleController;
import com.desafio_fullstack.projedata.domain.entities.EventSchedule;
import com.desafio_fullstack.projedata.domain.exceptions.NotFoundException;
import com.desafio_fullstack.projedata.domain.exceptions.AlredyExistsException;
import com.desafio_fullstack.projedata.domain.repositories.EventScheduleRepository;
import com.desafio_fullstack.projedata.domain.repositories.LocationRepository;
import com.desafio_fullstack.projedata.domain.repositories.PersonRepository;
import com.desafio_fullstack.projedata.infrastructure.dto.requests.EventScheduleRequest;
import com.desafio_fullstack.projedata.infrastructure.dto.response.EventScheduleResponse;

import jakarta.transaction.Transactional;

@Service
public class EventScheduleService {
    private final EventScheduleRepository eventScheduleRepository;
    private final PersonRepository personRepository;
    private final LocationRepository locationRepository;

    Logger logger = LoggerFactory.getLogger(EventScheduleController.class);

    public EventScheduleService(EventScheduleRepository eventScheduleRepository, PersonRepository personRepository,
            LocationRepository locationRepository) {
        this.eventScheduleRepository = eventScheduleRepository;
        this.personRepository = personRepository;
        this.locationRepository = locationRepository;
    }

    public EventSchedule findById(Long id) {
        return eventScheduleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with id " + id + " not found"));

    }

    public List<EventScheduleResponse> findAll(Pageable page) {
        return eventScheduleRepository.findAll(page).toList().stream().map(p -> EventScheduleResponse.fromEntity(p))
                .toList();
    }

    @Transactional
    public EventScheduleResponse save(EventScheduleRequest event) {
        // check if scheduledAt is in the past
        if (event.scheduledAt().isBefore(LocalDateTime.now())) {
            logger.error("Scheduled date and time cannot be in the past");
            throw new RuntimeException("A data e hora do evento não pode ser no passado");
        }
        // check if event schedule with same scheduledAt already exists
        if (this.existsByScheduledAt(event.scheduledAt())) {
            logger.error("An event is already scheduled for this time");
            throw new AlredyExistsException("Já existe um evento agendado para esse horário");
        }
        var newEvent = event.toEntity();
        var person = personRepository.findById(event.person_id()).orElse(null);
        var location = locationRepository.findById(event.location_id()).orElse(null);
        newEvent.setPerson(person);
        newEvent.setLocation(location);
        return EventScheduleResponse.fromEntity(eventScheduleRepository.save(newEvent));
    }

    public EventScheduleResponse update(Long id, EventScheduleRequest event) {
        if (event.scheduledAt().isBefore(LocalDateTime.now())) {
            logger.error("Scheduled date and time cannot be in the past");
            throw new RuntimeException("A data e hora do evento não pode ser no passado");
        }
        if (this.existsByScheduledAt(event.scheduledAt())) {
            throw new AlredyExistsException("Já existe um evento agendado para esse horário");
        }
        if (!eventScheduleRepository.existsById(id)) {
            logger.error("Product not found");
            throw new NotFoundException("Produto não encontrado");
        }
        EventSchedule existedEvent = this.findById(id);
        existedEvent.setName(event.name());
        existedEvent.setDescription(event.description());
        existedEvent.setCount(event.count());
        var person = personRepository.findById(event.person_id()).orElse(null);
        var location = locationRepository.findById(event.location_id()).orElse(null);
        existedEvent.setPerson(person);
        existedEvent.setLocation(location);

        return EventScheduleResponse.fromEntity(eventScheduleRepository.save(existedEvent));
    }

    public void delete(Long id) {
        if (!eventScheduleRepository.existsById(id)) {
            throw new NotFoundException("Produto não encontrado");
        }
        EventSchedule existedEvent = this.findById(id);
        eventScheduleRepository.delete(existedEvent);
    }

    public boolean existsByScheduledAt(LocalDateTime datetime) {
        return eventScheduleRepository.findByScheduledAtIn(datetime) != null ? true :
        false;
    }
}
