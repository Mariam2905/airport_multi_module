package com.example.services;

import com.example.model.*;
import com.example.repository.*;
import com.example.model.comparator_for_passenger.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;

    private final PassInTripRepository passInTripRepository;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository, PassInTripRepository passInTripRepository) {
        this.passengerRepository = passengerRepository;
        this.passInTripRepository = passInTripRepository;
    }

    /**
     * Method gets all passenger data from passenger table.
     *
     * @return all information about all the passengers from table passenger.
     */
    @Transactional
    public List<Passenger> getAll() {
        return passengerRepository.findAll();
    }

    /**
     * Method gets the passenger data by passenger_id.
     *
     * @return passenger data
     */
    public Optional<Passenger> getById(Long id) {
        boolean exists = passengerRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Passenger with id " + id + " does not exist.");
        }
        return passengerRepository.findById(id);
    }

    /**
     * Deletes a passenger from an entity by id
     *
     * @param id;
     */
    @Transactional
    public void deleteById(Long id) {
        boolean exists = passengerRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Passenger with id " + id + " does not exist.");
        }
        List<PassInTrip> passInTripList = passInTripRepository.findPassInTripsByPassengerId(id);
        if (passInTripList != null) {
            passInTripRepository.deleteAll(passInTripList);
        }
        passengerRepository.deleteById(id);
    }

    /**
     * Saves a new passenger into our passenger entity.
     *
     * @param passenger will be saved;
     */
    @Transactional
    public void savePassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    /**
     * Updates passengers all or only needed fields by id.
     *
     * @param id;
     * @param name;
     * @param phone;
     * @param country;
     * @param city;
     */
    @Transactional
    public void updatePassenger(Long id, String name, String phone,
                                String country, String city) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Passenger with id " + id + " does not exist."));
        if (name != null && name.length() > 0 && !Objects.equals(passenger.getName(), name)) {
            passenger.setName(name);
        }
        if (phone != null && phone.length() > 0 && !Objects.equals(passenger.getPhone(), phone)) {
            Optional<Passenger> passengerOptional = passengerRepository
                    .findPassengerByPhone(phone);
            if (passengerOptional.isPresent()) {
                throw new IllegalStateException("Phone number already exists.");
            }
            passenger.setPhone(phone);
        }
        if (country != null && country.length() > 0 && !Objects.equals(passenger.getCountry(), country)) {
            passenger.setCountry(country);
        }
        if (city != null && city.length() > 0 && !Objects.equals(passenger.getCity(), city)) {
            passenger.setCity(city);
        }
    }

    /**
     * Method gets passenger data by pages.
     *
     * @param offset  from which passenger should be shown the page.
     * @param perPage how many passengers are being shown in a page
     * @param sort    by which column of the passenger table
     * @return the sorted passenger data from some point to some point.
     */
    @Transactional
    public List<Passenger> get(int offset, int perPage, String sort) {
        List<Passenger> passengers = passengerRepository.findAll();
        List<Passenger> result = new ArrayList<>();
        for (int i = offset; i <= perPage + offset; i++) {
            result.add(passengers.get(i));
        }
        switch (sort) {
            case "sortById" -> result.sort(new SortById());
            case "sortByName" -> result.sort(new SortByName());
            case "sortByPhone" -> result.sort(new SortByPhone());
            case "sortByCity" -> result.sort(new SortByCity());
            case "sortByCountry" -> result.sort(new SortByCountry());
            default -> {
                System.out.println("Choose passenger`s field for sorting");
                get(offset, perPage, sort);
            }
        }
        return result;
    }
}
