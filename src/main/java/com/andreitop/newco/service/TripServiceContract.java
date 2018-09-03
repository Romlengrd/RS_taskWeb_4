package com.andreitop.newco.service;

import com.andreitop.newco.dto.TripDto;

import java.util.List;

public interface TripServiceContract<T extends TripDto> {

    List<T> findAll();

    T findById(Long id);

    long save(T trip);

    void delete(Long id);

    void update(T newTrip);
}
