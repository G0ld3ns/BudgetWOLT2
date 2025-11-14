package com.example.budgetwolt2.fxControllers;

import com.example.budgetwolt2.hibernateControl.CustomHibernate;
import com.example.budgetwolt2.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class ChatForm {
    @FXML
    public ListView chatBox;
    @FXML
    public TextArea messageText;

    private User currentUser;

    private FoodOrder currentFoodOrder;

    private EntityManagerFactory entityManagerFactory;
    private CustomHibernate customHibernate;

    private void setData(EntityManagerFactory entityManagerFactory, User currentUser, FoodOrder currentFoodOrder) {
        this.entityManagerFactory = entityManagerFactory;
        this.currentUser = currentUser;
        this.currentFoodOrder = currentFoodOrder;
        this.customHibernate = new CustomHibernate(entityManagerFactory);

    }

    public void sendMessage() {
        if (currentFoodOrder.getChat() == null) {
            Chat chat = new Chat("Chat no " + currentFoodOrder.getName(), currentFoodOrder);
            customHibernate.create(chat);
        }

        FoodOrder foodOrder = customHibernate.getEntityById(FoodOrder.class, currentFoodOrder.getId());
        Review message = new Review(messageText.getText(), (BasicUser) currentUser, foodOrder.getChat());
        customHibernate.create(message);
    }
}
