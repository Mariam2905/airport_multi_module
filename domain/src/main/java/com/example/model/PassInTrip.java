package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "passintrip")
public class PassInTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tripnumber")
    private Long tripId;
    @Column(name = "passengernumber")
    private Long passengerId;
    @Column(name = "date")
    private String date;
    @Column(name = "place")
    private String place;

    public PassInTrip() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public PassInTrip(Long tripId, Long passengerId, String date, String place) {
        this.tripId = tripId;
        this.passengerId = passengerId;
        this.date = date;
        this.place = place;
    }
}