package com.example.budgetwolt2.fxControllers;

import com.example.budgetwolt2.model.CuisineType;
import com.example.budgetwolt2.model.VehicleType;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class UserTableParameters {
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty userType = new SimpleStringProperty();
    private SimpleStringProperty login = new SimpleStringProperty();
    private SimpleStringProperty password = new SimpleStringProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty surname = new SimpleStringProperty();
    private SimpleStringProperty phone = new SimpleStringProperty();
    private SimpleStringProperty address = new SimpleStringProperty();
    private SimpleStringProperty restaurantName = new SimpleStringProperty();

    private ObjectProperty<CuisineType> cuisineType = new SimpleObjectProperty<>();
    private SimpleDoubleProperty deliveryFee = new SimpleDoubleProperty();
    private ObjectProperty<LocalTime> closingTime = new SimpleObjectProperty<>();
    private ObjectProperty<LocalTime> openingTime = new SimpleObjectProperty<>();
    private ObjectProperty<LocalDate> bDate = new SimpleObjectProperty<>();
    private ObjectProperty<VehicleType> vehicleType = new SimpleObjectProperty<>();

    // id
    public int getId() { return id.get(); }
    public void setId(int id) { this.id.set(id); }
    public IntegerProperty idProperty() { return id; }

    // userType
    public String getUserType() { return userType.get(); }
    public void setUserType(String userType) { this.userType.set(userType); }
    public StringProperty userTypeProperty() { return userType; }

    // login
    public String getLogin() { return login.get(); }
    public void setLogin(String login) { this.login.set(login); }
    public StringProperty loginProperty() { return login; }

    // password
    public String getPassword() { return password.get(); }
    public void setPassword(String password) { this.password.set(password); }
    public StringProperty passwordProperty() { return password; }

    // name
    public String getName() { return name.get(); }
    public void setName(String name) { this.name.set(name); }
    public StringProperty nameProperty() { return name; }

    // surname
    public String getSurname() { return surname.get(); }
    public void setSurname(String surname) { this.surname.set(surname); }
    public StringProperty surnameProperty() { return surname; }

    // phone
    public String getPhone() { return phone.get(); }
    public void setPhone(String phone) { this.phone.set(phone); }
    public StringProperty phoneProperty() { return phone; }

    // address
    public String getAddress() { return address.get(); }
    public void setAddress(String address) { this.address.set(address); }
    public StringProperty addressProperty() { return address; }

    // restaurantName
    public String getRestaurantName() { return restaurantName.get(); }
    public void setRestaurantName(String restaurantName) { this.restaurantName.set(restaurantName); }
    public StringProperty restaurantNameProperty() { return restaurantName; }

    // cuisineType
    public CuisineType getCuisineType() { return cuisineType.get(); }
    public void setCuisineType(CuisineType cuisineType) { this.cuisineType.set(cuisineType); }
    public ObjectProperty<CuisineType> cuisineTypeProperty() { return cuisineType; }

    // deliveryFee
    public double getDeliveryFee() { return deliveryFee.get(); }
    public void setDeliveryFee(double deliveryFee) { this.deliveryFee.set(deliveryFee); }
    public DoubleProperty deliveryFeeProperty() { return deliveryFee; }

    // closingTime
    public LocalTime getClosingTime() { return closingTime.get(); }
    public void setClosingTime(LocalTime closingTime) { this.closingTime.set(closingTime); }
    public ObjectProperty<LocalTime> closingTimeProperty() { return closingTime; }

    // openingTime
    public LocalTime getOpeningTime() { return openingTime.get(); }
    public void setOpeningTime(LocalTime openingTime) { this.openingTime.set(openingTime); }
    public ObjectProperty<LocalTime> openingTimeProperty() { return openingTime; }

    // bDate
    public LocalDate getBDate() { return bDate.get(); }
    public void setBDate(LocalDate bDate) { this.bDate.set(bDate); }
    public ObjectProperty<LocalDate> bDateProperty() { return bDate; }

    // vehicleType
    public VehicleType getVehicleType() { return vehicleType.get(); }
    public void setVehicleType(VehicleType vehicleType) { this.vehicleType.set(vehicleType); }
    public ObjectProperty<VehicleType> vehicleTypeProperty() { return vehicleType; }
}
