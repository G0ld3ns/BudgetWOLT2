package com.example.budgetwolt2.fxControllers;

import com.example.budgetwolt2.hibernateControl.GenericHibernate;
import com.example.budgetwolt2.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class UserForm implements Initializable {
    @FXML
    public RadioButton userRadio;
    @FXML
    public RadioButton restaurantRadio;
    @FXML
    public RadioButton clientRadio;
    @FXML
    public RadioButton driverRadio;
    @FXML
    public TextField loginField;
    @FXML
    public TextField passwordField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField surnameField;
    @FXML
    public TextField phoneField;
    @FXML
    public TextField addressField;
    @FXML
    public VBox userPane;
    @FXML
    public VBox RestaurantPane;
    @FXML
    public TextField restaurantNameField;
    @FXML
    public TextField restaurantAddressField;
    @FXML
    public ComboBox<CuisineType> cuisineType;
    @FXML
    public TextField deliveryFee;
    @FXML
    public TextField openingTime;
    @FXML
    public TextField closingTime;
    @FXML
    public VBox driverPane;
    @FXML
    public DatePicker driverBirth;
    @FXML
    public ComboBox<VehicleType> vehicleField;

    private EntityManagerFactory entityManagerFactory;
    private GenericHibernate genericHibernate;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userPane.setVisible(false);
        RestaurantPane.setVisible(false);
        driverPane.setVisible(false);
        this.cuisineType.getItems().setAll(CuisineType.values());
        this.vehicleField.getItems().setAll(VehicleType.values());
    }


    public void setData(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        this.genericHibernate = new GenericHibernate(entityManagerFactory);
    }



    public void disableFields() {

        if (userRadio.isSelected()) {
            userPane.setVisible(false);
            RestaurantPane.setVisible(false);
            driverPane.setVisible(false);

        } else if (restaurantRadio.isSelected()) {
            userPane.setVisible(false);
            RestaurantPane.setVisible(true);
            driverPane.setVisible(false);
        } else if (clientRadio.isSelected()) {
            userPane.setVisible(true);
            RestaurantPane.setVisible(false);
            driverPane.setVisible(false);
        } else if (driverRadio.isSelected()) {
            userPane.setVisible(false);
            RestaurantPane.setVisible(false);
            driverPane.setVisible(true);

        }
    }
    public void createNewUser() {
        if (userRadio.isSelected()) {
            User user = new User(loginField.getText(), passwordField.getText(), nameField.getText(), surnameField.getText(), phoneField.getText());
            genericHibernate.create(user);
        } else if  (clientRadio.isSelected()) {
            BasicUser basicUser = new BasicUser(loginField.getText(), passwordField.getText(), nameField.getText(), surnameField.getText(), phoneField.getText(), addressField.getText());
            genericHibernate.create(basicUser);
        }else if  (restaurantRadio.isSelected()) {
            Restaurant restaurant = new Restaurant(loginField.getText(), passwordField.getText(), nameField.getText(), surnameField.getText(), phoneField.getText(),  restaurantNameField.getText(), addressField.getText(), cuisineType.getValue(), Double.parseDouble(deliveryFee.getText()), LocalTime.parse(openingTime.getText()), LocalTime.parse(closingTime.getText()));
            genericHibernate.create(restaurant);
        }
        else if  (driverRadio.isSelected()) {
            Driver driver = new Driver(loginField.getText(), passwordField.getText(), nameField.getText(), surnameField.getText(), phoneField.getText(), addressField.getText(), driverBirth.getValue(), vehicleField.getValue());
            genericHibernate.create(driver);
        }
    }

}
