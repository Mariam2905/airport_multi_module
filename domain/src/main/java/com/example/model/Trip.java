package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "companynumber")
    private int companyId;
    @Column(name = "airline")
    private String aircraftModel;
    @Column(name = "city_from")
    private String townFrom;
    @Column(name = "city_to")
    private String townTo;
    @Column(name = "timedeparture")
    private String timeOut;
    @Column(name = "timearrival")
    private String timeIn;

    public Trip() {
    }

    public Trip(int companyId, String aircraftModel, String townFrom, String townTo, String timeOut, String timeIn) {
        this.companyId = companyId;
        this.aircraftModel = aircraftModel;
        this.townFrom = townFrom;
        this.townTo = townTo;
        this.timeOut = timeOut;
        this.timeIn = timeIn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getAircraftModel() {
        return aircraftModel;
    }

    public void setAircraftModel(String aircraftModel) {
        this.aircraftModel = aircraftModel;
    }

    public String getTownFrom() {
        return townFrom;
    }

    public void setTownFrom(String townFrom) {
        this.townFrom = townFrom;
    }

    public String getTownTo() {
        return townTo;
    }

    public void setTownTo(String townTo) {
        this.townTo = townTo;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    @Override
    public String toString() {
        return "Trip [id = " + id + ", companyId = " + companyId + ", aircraftModel = " + aircraftModel + ", townFrom = "
                + townFrom + ", townTo = " + townTo + ", timeOut = " + timeOut + ", timeIn = " + timeIn + "]\n";
    }
}
