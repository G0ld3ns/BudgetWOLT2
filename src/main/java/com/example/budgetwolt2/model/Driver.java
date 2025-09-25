package com.example.budgetwolt2.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter

public class Driver extends BasicUser{
    private String licence;
    private LocalDate bDate;
    private VehicleType vehicleType;

    public Driver(String login, String password, String name, String surname, String phoneNumber, String address, List<FoodOrder> myOrders, List<Review> myReviews, List<Review> feedback, String licence, LocalDate bDate, VehicleType vehicleType) {
        super(login, password, name, surname, phoneNumber, address, myOrders, myReviews, feedback);
        this.licence = licence;
        this.bDate = bDate;
        this.vehicleType = vehicleType;
    }
}
