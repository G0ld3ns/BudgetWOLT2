module com.example.budgetwolt2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.budgetwolt2 to javafx.fxml;
    exports com.example.budgetwolt2;
}