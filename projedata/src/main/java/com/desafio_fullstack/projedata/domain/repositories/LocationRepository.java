package com.desafio_fullstack.projedata.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio_fullstack.projedata.domain.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
