package com.example.budgetwolt2.fxControllers;

import com.example.budgetwolt2.hibernateControl.CustomHibernate;
import com.example.budgetwolt2.model.BasicUser;
import com.example.budgetwolt2.model.Driver;
import com.example.budgetwolt2.model.Restaurant;
import com.example.budgetwolt2.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.hibernate.metamodel.model.domain.internal.BasicSqmPathSource;

public class MainForm {
    @FXML
    public Tab userTab;
    @FXML
    public Tab oerderTab;
    @FXML
    public Tab menuTab;
    @FXML
    public TabPane tabPane;

    private EntityManagerFactory entityManagerFactory;

    private CustomHibernate customHibernate;
    private User currentUser;

    public void setData(EntityManagerFactory entityManagerFactory, User user) {
        this.entityManagerFactory = entityManagerFactory;
        this.currentUser = user;
        this.customHibernate = new CustomHibernate(entityManagerFactory);
        setUserFormVisibility();
    }

    private void setUserFormVisibility() {
        if (currentUser instanceof Restaurant){
            tabPane.getTabs().remove(userTab);
            tabPane.getTabs().remove(oerderTab);
        }
        else if (currentUser instanceof BasicUser){
            tabPane.getTabs().remove(userTab);
            tabPane.getTabs().remove(menuTab);
        }else if (currentUser instanceof Driver){
            tabPane.getTabs().remove(userTab);
            tabPane.getTabs().remove(menuTab);
        }else if (currentUser instanceof User) {
            //nebutina kazko cia idunno
        }
    }

    public void reloadTableData() {
        //-45.38
    }
}
