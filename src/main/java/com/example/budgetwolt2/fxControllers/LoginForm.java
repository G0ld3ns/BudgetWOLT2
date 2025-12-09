package com.example.budgetwolt2.fxControllers;

import com.example.budgetwolt2.hibernateControl.CustomHibernate;
import com.example.budgetwolt2.model.BasicUser;
import com.example.budgetwolt2.model.Driver;
import com.example.budgetwolt2.model.User;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginForm {
    @FXML
    public TextField loginField;
    @FXML
    public TextField passwordField;

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("kurs") ;

    public void validateAndLoad() throws IOException {
        CustomHibernate customHibernate = new CustomHibernate(entityManagerFactory);
        User user = customHibernate.getUserByUsername(loginField.getText(), passwordField.getText());
        if (user != null) {
            if (user instanceof BasicUser || user instanceof Driver) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login not allowed");
                alert.setHeaderText("This account type cannot use the desktop app");
                alert.setContentText("Clients and drivers can only use the mobile application.");
                alert.showAndWait();
                return; // do NOT open main window
            }


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/budgetwolt2/main-form.fxml"));
        Parent parent = fxmlLoader.load();

        MainForm mainForm = fxmlLoader.getController();
        mainForm.setData(entityManagerFactory, user);

        Scene scene = new Scene(parent);
        Stage stage = (Stage) passwordField.getScene().getWindow();
        stage.setTitle("Main");
        stage.setScene(scene);
        stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Error trying to login");
            alert.setContentText("Given credentials are incorrect! Try again!");

            alert.showAndWait();

        }
    }

    public void registerNewUser() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/budgetwolt2/user-form.fxml"));
        Parent parent = fxmlLoader.load();
        UserForm userForm = fxmlLoader.getController();
        userForm.setData(entityManagerFactory);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Register");
        stage.setScene(scene);
        stage.show();
    }
}
