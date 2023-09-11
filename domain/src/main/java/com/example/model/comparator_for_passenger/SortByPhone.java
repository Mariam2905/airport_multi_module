package com.example.model.comparator_for_passenger;

import com.example.model.Passenger;

import java.util.Comparator;

public class SortByPhone implements Comparator<Passenger> {
    @Override
    public int compare(Passenger o1, Passenger o2) {
        return o1.getPhone().compareTo(o2.getPhone());
    }
}