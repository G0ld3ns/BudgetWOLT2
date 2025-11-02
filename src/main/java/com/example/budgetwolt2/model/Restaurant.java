package com.example.budgetwolt2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Restaurant extends BasicUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String restaurantName;
    private String phone;
    private String email;
    @Enumerated(EnumType.STRING)
    private CuisineType cuisineType;
    private double deliveryFee;
    private LocalTime openingTime;
    private LocalTime closingTime;
    @OneToMany
    private List<Cuisine> menuItems;


    public Restaurant(String login, String password, String name, String surname, String phoneNumber, String address, String restaurantName, CuisineType cuisineType, double deliveryFee,  LocalTime closingTime, LocalTime openingTime) {
        super(login, password, name, surname, phoneNumber, address);
        this.restaurantName = restaurantName;
        this.closingTime = closingTime;
        this.openingTime = openingTime;
        this.deliveryFee = deliveryFee;
        this.cuisineType = cuisineType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CuisineType getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(CuisineType cuisineType) {
        this.cuisineType = cuisineType;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public List<Cuisine> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<Cuisine> menuItems) {
        this.menuItems = menuItems;
    }
}
