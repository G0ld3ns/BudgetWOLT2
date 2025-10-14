package com.example.budgetwolt2.fxControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserForm {
    @FXML
    private Label infoLabel;

    @FXML
    public void initialize() {
        infoLabel.setText("This is the User Form!");
    }
}
