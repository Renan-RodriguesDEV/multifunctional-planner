package com.desafio_fullstack.projedata.infrastructure.dto.requests;

import com.desafio_fullstack.projedata.domain.entities.Location;

public record LocationRequest(String address) {
    public Location toEntity() {
        return new Location(address);
    }
}
