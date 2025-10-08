package com.example.budgetwolt2.fxControllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class LoginForm {
    @FXML
    private Label welcomeText;
    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;

    public void validateAndLoad() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if ("admin".equals(username) && "1234".equals(password)) {
            welcomeText.setText("Login successful!");
        } else {
            welcomeText.setText("Password incorrect!A");
        }
    }
}
