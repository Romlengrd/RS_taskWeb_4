package com.andreitop.newco.controller;

import com.andreitop.newco.dto.TripDto;

import java.util.List;

public interface TripsControllerContract<T extends TripDto> {
    List<T> findAll();

    T findById(final Long id);

    void create(final T trip);

    void delete(final Long id);

    void update(final T newTrip);

}
