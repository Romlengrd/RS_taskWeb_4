package com.andreitop.newco.repository;

import com.andreitop.newco.dto.TripDto;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TripRepository  implements TripRepositoryContract<TripDto>{

    private final List<TripDto> trips = new ArrayList<>();

    public List<TripDto> findAll() {
        return trips;
    }

    public TripDto findById(final Long id) {
        return trips.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public long save(final TripDto trip) {
        long id = (long) (trips.size()+1);
        trip.setId(id);
        trips.add(trip);
        return id;
    }

    public void delete(final Long id) {
        trips.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .ifPresent(trips::remove);
    }

    public void update(final TripDto newTrip) {
        trips.stream()
                .filter(t -> t.getId().equals(newTrip.getId()))
                .findFirst()
                .ifPresent(t -> trips.set(trips.indexOf(t), newTrip));
    }
}
