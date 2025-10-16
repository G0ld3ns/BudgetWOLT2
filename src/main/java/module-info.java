module com.example.budgetwolt2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.naming;
    requires mysql.connector.j;
    requires jakarta.persistence;
//    Optional thingies
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.budgetwolt2 to javafx.fxml, org.hibernate.orm.core, jakarta.persistence;
    exports com.example.budgetwolt2;
    opens com.example.budgetwolt2.fxControllers to javafx.fxml;
    exports com.example.budgetwolt2.fxControllers;
    opens com.example.budgetwolt2.model to org.hibernate.orm.core;
    exports com.example.budgetwolt2.model;
}