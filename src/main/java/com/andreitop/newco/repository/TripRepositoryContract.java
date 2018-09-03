package com.andreitop.newco.repository;

import com.andreitop.newco.dto.TripDto;

import java.util.List;

public interface TripRepositoryContract<T extends TripDto> {

     List<T> findAll();

     T findById(final Long id);

     long save(final T trip);

     void delete(final Long id);

     void update(final T newTrip);
}
