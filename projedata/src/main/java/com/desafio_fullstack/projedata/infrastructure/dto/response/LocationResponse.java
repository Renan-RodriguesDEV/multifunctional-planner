package com.desafio_fullstack.projedata.infrastructure.dto.response;

import com.desafio_fullstack.projedata.domain.entities.Location;

public record LocationResponse(Long id, String address) {
    public Location toEntity() {
        return new Location(id, address);
    }

    public static LocationResponse fromEntity(Location location) {
        return new LocationResponse(location.getId(), location.getAddress());
    }
}
