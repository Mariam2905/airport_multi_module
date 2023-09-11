package com.example.controller;

import com.example.model.*;
import com.example.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trip")
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/getAll")
    public List<Trip> getAll() {
        return tripService.getAll();
    }

    @GetMapping("/get/{tripId}")
    public Optional<Trip> getById(@PathVariable("tripId") Long id) {
        return tripService.getById(id);
    }

    @PostMapping("/save")
    public Long save(@RequestBody Trip trip) {
        tripService.save(trip);
        return trip.getId();
    }

    @PostMapping("/delete/{tripId}")
    public void deleteById(@PathVariable("tripId") Long id) {
        tripService.delete(id);
    }

    @PutMapping(path = "{tripId}")
    public void updatePassenger(@PathVariable("tripId") Long tripId,
                                @RequestParam(required = false) Integer companyId,
                                @RequestParam(required = false) String aircraftModel,
                                @RequestParam(required = false) String townFrom,
                                @RequestParam(required = false) String townTo,
                                @RequestParam(required = false) String timeOut,
                                @RequestParam(required = false) String timeIn) {
        tripService.updateTrip(tripId, companyId, aircraftModel, townFrom, townTo, timeOut, timeIn);
    }

    @GetMapping("/townTo/{town}")
    public List<Trip> getTownTo(@PathVariable("town") String town) {
        return tripService.getTownTo(town);
    }

    @GetMapping("/townFrom/{town}")
    public List<Trip> getTownFrom(@PathVariable("town") String town) {
        return tripService.getTownFrom(town);
    }
}
