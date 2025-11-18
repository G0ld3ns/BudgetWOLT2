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

import java.util.List;

public class ChatForm {
    @FXML
    public ListView chatBox;
    @FXML
    public TextArea messageText;

    private User currentUser;

    private FoodOrder currentFoodOrder;

    private EntityManagerFactory entityManagerFactory;
    private CustomHibernate customHibernate;

    public void setData(EntityManagerFactory entityManagerFactory, User currentUser, FoodOrder currentFoodOrder) {
        this.entityManagerFactory = entityManagerFactory;
        this.currentUser = currentUser;
        this.currentFoodOrder = currentFoodOrder;
        this.customHibernate = new CustomHibernate(entityManagerFactory);
        loadMessages();
    }

    public void sendMessage() {
        String text = messageText.getText();
        if (text == null || text.isBlank() || currentFoodOrder == null || currentUser == null) {
            return;
        }

        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();

            FoodOrder order = em.find(FoodOrder.class, currentFoodOrder.getId());
            if (order == null) {
                em.getTransaction().rollback();
                return;
            }

            Chat chat = order.getChat();
            if (chat == null) {
                chat = new Chat("Chat no " + order.getName(), order);
                order.setChat(chat);
                em.persist(chat);
            }

            User author = em.find(User.class, currentUser.getId());
            if (author == null) {
                em.getTransaction().rollback();
                throw new IllegalStateException("User not found for id " + currentUser.getId());
            }

            Review message = new Review(text, author, chat);
            em.persist(message);

            String old = chat.getChatText();
            String prefix = (old == null || old.isBlank()) ? "" : old + "\n";
            chat.setChatText(prefix + author.getLogin() + ": " + text);

            em.getTransaction().commit();
            messageText.clear();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        loadMessages();
    }
    private void loadMessages() {
        chatBox.getItems().clear();
        if (currentFoodOrder == null) return;

        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            FoodOrder order = em.find(FoodOrder.class, currentFoodOrder.getId());
            if (order == null) return;

            Chat chat = order.getChat();
            if (chat == null) return;

            List<Review> messages = em.createQuery(
                            "select r from Review r " +
                                    "where r.chat = :chat " +
                                    "order by r.dateCreated, r.id",
                            Review.class
                    ).setParameter("chat", chat)
                    .getResultList();

            chatBox.getItems().addAll(messages);
        } finally {
            em.close();
        }
    }

}
