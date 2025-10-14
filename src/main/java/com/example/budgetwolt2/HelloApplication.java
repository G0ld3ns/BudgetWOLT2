package com.example.budgetwolt2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load the login FXML
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/example/budgetwolt2/login-form.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load(), 400, 300);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}