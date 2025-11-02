package com.example.budgetwolt2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    private  String name;
    private Double price;
    @ManyToOne
    private BasicUser buyer;
    @ManyToMany
    private List<Cuisine> cuisineList;
    @OneToOne
    private Chat chat;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BasicUser getBuyer() {
        return buyer;
    }

    public void setBuyer(BasicUser buyer) {
        this.buyer = buyer;
    }

    public List<Cuisine> getCuisineList() {
        return cuisineList;
    }

    public void setCuisineList(List<Cuisine> cuisineList) {
        this.cuisineList = cuisineList;
    }
}
