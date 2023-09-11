package com.example.controller;

import com.example.model.*;
import com.example.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/passInTrip")
public class PassInTripController {

    private final PassInTripService passInTripService;

    @Autowired
    public PassInTripController(PassInTripService passInTripService) {
        this.passInTripService = passInTripService;
    }

    @GetMapping("/get/{id}")
    public Optional<PassInTrip> getById(@PathVariable("id") Long id) {
        return passInTripService.getById(id);
    }

    @GetMapping("/getAll")
    public List<PassInTrip> getAll() {
        return passInTripService.getAll();
    }

    @PostMapping("/save")
    public Long save(@RequestBody PassInTrip passInTrip) {
        passInTripService.save(passInTrip);
        return passInTrip.getId();
    }

    @PostMapping("/registerTrip/{tripId}/{passengerId}/{date}/{place}")
    public void registerTrip(@PathVariable("tripId") Long tripId, @PathVariable("passengerId") Long passengerId,
                             @PathVariable("date") String date, @PathVariable("place") String place) {
        passInTripService.registerTrip(tripId, passengerId, date, place);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        passInTripService.deleteById(id);
    }

    @DeleteMapping("/cancel/{passengerId}/{tripId}")
    public void cancelTrip(@PathVariable("passengerId") Long passengerId, @PathVariable("tripId") Long tripId) {
        passInTripService.cancelTrip(passengerId, tripId);
    }
}
