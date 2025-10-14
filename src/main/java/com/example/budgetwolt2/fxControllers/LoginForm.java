package com.example.budgetwolt2.fxControllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


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
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(
                        getClass().getResource("/com/example/budgetwolt2/main-form.fxml")
                );

                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = (Stage) usernameField.getScene().getWindow();

                stage.setScene(scene);
                stage.setTitle("Main Form");
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                welcomeText.setText("Error loading Main Form!");
            }

        } else {
            welcomeText.setText("Password incorrect!");
        }
    }

}
