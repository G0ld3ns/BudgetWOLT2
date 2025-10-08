module com.example.budgetwolt2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.dlsc.formsfx;

    opens com.example.budgetwolt2 to javafx.fxml;
    exports com.example.budgetwolt2;
    opens com.example.budgetwolt2.fxControllers to javafx.fxml;
    exports com.example.budgetwolt2.fxControllers to javafx.fxml;
}