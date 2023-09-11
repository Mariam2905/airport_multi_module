package com.example.services;

import com.example.model.*;
import com.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PassInTripService {
    PassengerService passengerService;
    TripService tripService;
    private final PassInTripRepository passInTripRepository;

    @Autowired
    public PassInTripService(PassInTripRepository passInTripRepository) {
        this.passInTripRepository = passInTripRepository;
    }

    /**
     * Gets all registered trips from PassInTrip entity
     *
     * @return list of registered trips
     */
    @Transactional
    public List<PassInTrip> getAll() {
        return passInTripRepository.findAll();
    }

    /**
     * Gets all registered trips by registration id number
     *
     * @return list of registered trips
     */
    @Transactional
    public Optional<PassInTrip> getById(Long id) {
        return passInTripRepository.findById(id);
    }

    /**
     * Saves a PassInTrip object into PassInTrip entity
     */
    @Transactional
    public void save(PassInTrip passInTrip) {
        passInTripRepository.save(passInTrip);
    }

    /**
     * Registers a trip with PassInTrip fields
     */
    public void registerTrip(Long tripId, Long passengerId, String date, String place) {
        Optional<Trip> trip = tripService.getById(tripId);
        Optional<Passenger> passenger = passengerService.getById(passengerId);
        if (passenger.isPresent() && trip.isPresent()) {
            passInTripRepository.save(new PassInTrip(tripId, passengerId, date, place));
        }
    }

    /**
     * Cancels an early registered trip by passengerId and tripId
     */

    @Transactional
    public void cancelTrip(Long passengerId, Long tripId) {
        passInTripRepository.deletePassInTripByPassengerIdAndTripId(passengerId, tripId);
    }

    /**
     * Deletes a registered trip from PassInTrip entity by registration id
     */
    @Transactional
    public void deleteById(Long id) {
        passInTripRepository.deleteById(id);
    }
}
