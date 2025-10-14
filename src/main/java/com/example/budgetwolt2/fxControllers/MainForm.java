package com.example.budgetwolt2.fxControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainForm {
    @FXML
    private Label welcomeLabel;

    @FXML
    public void initialize() {
        welcomeLabel.setText("Welcome!");
    }
}
