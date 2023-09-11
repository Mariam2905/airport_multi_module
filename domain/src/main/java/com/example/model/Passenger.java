package com.example.model;

import jakarta.persistence.*;

import java.util.Comparator;

@Entity
@Table(name = "passenger")
public class Passenger implements Comparator<Passenger> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passengerid")
    private Long id;
    @Column(name = "passengername")
    private String name;
    @Column(name = "passengerphone")
    private String phone;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;

    public Passenger() {
    }

    public Passenger(String name, String phone, String country, String city) {
        this.name = name;
        this.phone = phone;
        this.country = country;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Passenger [id = " + id + ", name = " + name + ", phone = " + phone + ", country = " + country + ", city = " + city
                + "]\n";
    }

    @Override
    public int compare(Passenger o1, Passenger o2) {
        return 0;
    }
}
