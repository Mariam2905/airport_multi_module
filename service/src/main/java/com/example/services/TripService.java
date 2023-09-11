package com.example.services;

import com.example.model.*;
import com.example.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TripService {

    private final TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    /**
     * Gets all trips from trip table
     *
     * @return list of trips
     */
    public List<Trip> getAll() {
        return tripRepository.findAll();
    }

    /**
     * Gets a trip from a table by given id.
     *
     * @param id;
     * @return data;
     */
    public Optional<Trip> getById(Long id) {
        boolean exists = tripRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Trip with id " + id + " does not exist.");
        }
        return tripRepository.findById(id);
    }

    /**
     * saves a new trip into the table
     *
     * @param trip;
     */
    public void save(Trip trip) {
        tripRepository.save(trip);
    }

    /**
     * Deletes a Trip by given id
     *
     * @param id;
     */
    public void delete(Long id) {
        tripRepository.deleteById(id);
    }

    /**
     * Updates all the fields or only needed fields of a trip
     *
     * @param tripId;
     * @param companyId;
     * @param aircraftModel;
     * @param townFrom;
     * @param townTo;
     * @param timeOut;
     * @param timeIn;
     */
    @Transactional
    public void updateTrip(Long tripId, Integer companyId, String aircraftModel, String townFrom,
                           String townTo, String timeOut, String timeIn) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalStateException("Trip with id " + tripId + " does not exist."));
        if (companyId != null && companyId > 0 && !Objects.equals(trip.getCompanyId(), companyId)) {
            trip.setCompanyId(companyId);
        }
        if (aircraftModel != null && aircraftModel.length() > 0 && !Objects.equals(trip.getAircraftModel(), aircraftModel)) {
            trip.setAircraftModel(aircraftModel);
        }
        if (townFrom != null && townFrom.length() > 0 && !Objects.equals(trip.getTownFrom(), townFrom)) {
            trip.setTownFrom(aircraftModel);
        }
        if (townTo != null && townTo.length() > 0 && !Objects.equals(trip.getTownTo(), townTo)) {
            trip.setTownTo(townTo);
        }
        if (timeOut != null && timeOut.length() > 0 && !Objects.equals(trip.getTimeOut(), timeOut)) {
            trip.setTimeOut(timeOut);
        }
        if (timeIn != null && !Objects.equals(trip.getTimeIn(), timeIn)) {
            trip.setTimeIn(timeIn);
        }
    }

    /**
     * Gets all the trips by destination city
     *
     * @param town from which the trip ends
     * @return trips list
     */
    public List<Trip> getTownTo(String town) {
        List<Trip> townTo = new ArrayList<>();
        List<Trip> tripList = tripRepository.findAll();
        for (Trip trip : tripList) {
            if (trip.getTownTo().equals(town)) {
                townTo.add(trip);
            }
        }
        return townTo;
    }

    /**
     * Gets all the trips by departure city
     *
     * @param town from which the trip begins
     * @return trips list
     */
    public List<Trip> getTownFrom(String town) {
        List<Trip> townFrom = new ArrayList<>();
        List<Trip> tripList = tripRepository.findAll();
        for (Trip trip : tripList) {
            if (trip.getTownFrom().equals(town)) {
                townFrom.add(trip);
            }
        }
        return townFrom;
    }
}
