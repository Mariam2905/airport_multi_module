package com.example.repository;

import com.example.model.PassInTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassInTripRepository extends JpaRepository<PassInTrip, Long> {

    void deletePassInTripByPassengerIdAndTripId(Long passengerId, Long tripId);

    List<PassInTrip> findPassInTripsByPassengerId(Long id);

    List<PassInTrip> deletePassInTripByTripId(Long id);
}
