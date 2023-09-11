package com.example.controller;

import com.example.model.*;
import com.example.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    private final PassengerService passengerService;

    @Autowired
    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping("/getAll")
    public List<Passenger> getAll() {
        return passengerService.getAll();
    }

    @GetMapping("/get/{passengerId}")
    public Optional<Passenger> getById(@PathVariable("passengerId") Long id) {
        return passengerService.getById(id);
    }

    @DeleteMapping("/delete/{passengerId}")
    public void deleteById(@PathVariable("passengerId") Long id) {
        passengerService.deleteById(id);
    }

    @PostMapping("/save")
    public Long saveCompany(@RequestBody Passenger passenger) {
        passengerService.savePassenger(passenger);
        return passenger.getId();
    }

    @PutMapping(path = "{passengerId}")
    public void updatePassenger(@PathVariable("passengerId") Long passengerId,
                                @RequestParam(required = false) String passName,
                                @RequestParam(required = false) String passPhone,
                                @RequestParam(required = false) String passCountry,
                                @RequestParam(required = false) String passCity) {
        passengerService.updatePassenger(passengerId, passName, passPhone, passCountry, passCity);
    }

    @GetMapping("/get/{offset}/{perPage}/{sort}")
    public List<Passenger> get(@PathVariable("offset") int offset, @PathVariable("perPage") int perPage, @PathVariable("sort") String sort) {
        return passengerService.get(offset, perPage, sort);

    }
}
