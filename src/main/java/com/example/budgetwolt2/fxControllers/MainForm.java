package com.example.budgetwolt2.fxControllers;

import com.example.budgetwolt2.hibernateControl.CustomHibernate;
import com.example.budgetwolt2.hibernateControl.GenericHibernate;
import com.example.budgetwolt2.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import org.hibernate.metamodel.model.domain.internal.BasicSqmPathSource;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class MainForm implements Initializable {
    @FXML
    public Tab userTab;
    @FXML
    public Tab oerderTab;
    @FXML
    public Tab menuTab;
    @FXML
    public TabPane tabPane;
    //User management tab
    @FXML
    public TableView<UserTableParameters> userTable;
    @FXML
    public TableColumn<UserTableParameters, Integer> idCol;
    @FXML
    public TableColumn<UserTableParameters, String> userTypeCol;
    @FXML
    public TableColumn<UserTableParameters, String> loginCol;
    @FXML
    public TableColumn<UserTableParameters, String> pswCol;
    @FXML
    public TableColumn<UserTableParameters, String> nameCol;
    @FXML
    public TableColumn<UserTableParameters, String> surnameCol;
    @FXML
    public TableColumn<UserTableParameters, String> phoneCol;
    @FXML
    public TableColumn<UserTableParameters, String> addressCol;
    @FXML
    public TableColumn<UserTableParameters, String> restaurantNameCol;
    @FXML
    public TableColumn<UserTableParameters, CuisineType> cuisineTypeCol;
    @FXML
    public TableColumn<UserTableParameters, Double> deliveryFeeCol;
    @FXML
    public TableColumn<UserTableParameters, LocalTime> closingTimeClol;
    @FXML
    public TableColumn<UserTableParameters, LocalTime> openingTimeCol;
    @FXML
    public TableColumn<UserTableParameters, LocalDate> bDateCol;
    @FXML
    public TableColumn<UserTableParameters, VehicleType> vehicleTypeCol;
    @FXML
    public TableColumn<UserTableParameters, Void> actionCol;
    //Menu Tab
    @FXML
    public ListView<Cuisine> menuListField;
    @FXML
    public TextField menuFoodNameField;
    @FXML
    public TextField menuFoodNameField1;
    @FXML
    public CheckBox spicyCheckBox;
    @FXML
    public CheckBox veganCheckBox;
    
    //Orders management Tab
    @FXML
    public ListView<FoodOrder> orderList;
    @FXML
    public ComboBox<BasicUser> clientList;
    @FXML
    public TextField titleField;
    @FXML
    public TextField priceField;
    @FXML
    public ComboBox<Restaurant> restaurantField;
    @FXML
    public ComboBox<OrderStatus> orderStatusField;
    @FXML
    public ComboBox<OrderStatus> filterStatus;
    @FXML
    public ComboBox<BasicUser> filterClients;
    @FXML
    public DatePicker filterDateCreated;
    @FXML
    public DatePicker filterDateUpdate;
    @FXML
    public ListView<Cuisine> orderFoodList;
    @FXML
    public ListView<Restaurant> restaurantList;
    @FXML
    public Button createOrderID;
    @FXML
    public Button updateOrderId;
    @FXML
    public Button deleteOrderID;
    
    //chat
    @FXML
    public Tab chatTab;
    @FXML
    public ListView<Chat> allChat;
    @FXML
    public ListView<Review> chtMessagesList;


    private ObservableList<UserTableParameters> data = FXCollections.observableArrayList();


    private EntityManagerFactory entityManagerFactory;

    private CustomHibernate customHibernate;
    private GenericHibernate genericHibernate;
    private User currentUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userTable.setEditable(true);
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        userTypeCol.setCellValueFactory(new PropertyValueFactory<>("userType"));
        loginCol.setCellValueFactory(new PropertyValueFactory<>("login"));
        pswCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        pswCol.setCellFactory(TextFieldTableCell.forTableColumn());
        pswCol.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setPassword(event.getNewValue());
            User user = customHibernate.getEntityById(User.class, event.getTableView().getItems().get(event.getTablePosition().getRow()).getId());
            user.setPassword(event.getNewValue());
            customHibernate.update(user);
        });
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(event -> {
            event.getTableView().getItems().get(event.getTablePosition().getRow()).setName(event.getNewValue());
            User user = customHibernate.getEntityById(User.class, event.getTableView().getItems().get(event.getTablePosition().getRow()).getId());
            user.setName(event.getNewValue());
            customHibernate.update(user);
        });
        // Surname
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameCol.setOnEditCommit(event -> {
            UserTableParameters row = event.getTableView().getItems()
                    .get(event.getTablePosition().getRow());
            row.setSurname(event.getNewValue());

            User user = customHibernate.getEntityById(
                    User.class,
                    row.getId()
            );
            user.setSurname(event.getNewValue());
            customHibernate.update(user);
        });

        // Phone number
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phoneCol.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneCol.setOnEditCommit(event -> {
            UserTableParameters row = event.getTableView().getItems()
                    .get(event.getTablePosition().getRow());
            row.setPhone(event.getNewValue());

            User user = customHibernate.getEntityById(
                    User.class,
                    row.getId()
            );
            user.setPhoneNumber(event.getNewValue());
            customHibernate.update(user);
        });

        // Address (BasicUser, Driver, Restaurant – all have address)
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        addressCol.setCellFactory(TextFieldTableCell.forTableColumn());
        addressCol.setOnEditCommit(event -> {
            UserTableParameters row = event.getTableView().getItems()
                    .get(event.getTablePosition().getRow());
            row.setAddress(event.getNewValue());

            User user = customHibernate.getEntityById(
                    User.class,
                    row.getId()
            );

            if (user instanceof BasicUser basicUser) {
                basicUser.setAddress(event.getNewValue());
                customHibernate.update(basicUser);
            } else if (user instanceof Driver driver) {
                driver.setAddress(event.getNewValue());
                customHibernate.update(driver);
            } else if (user instanceof Restaurant restaurant) {
                restaurant.setAddress(event.getNewValue());
                customHibernate.update(restaurant);
            }
        });

        // Restaurant name (only Restaurant)
        restaurantNameCol.setCellValueFactory(new PropertyValueFactory<>("restaurantName"));
        restaurantNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        restaurantNameCol.setOnEditCommit(event -> {
            UserTableParameters row = event.getTableView().getItems()
                    .get(event.getTablePosition().getRow());
            row.setRestaurantName(event.getNewValue());

            User user = customHibernate.getEntityById(
                    User.class,
                    row.getId()
            );
            if (user instanceof Restaurant restaurant) {
                restaurant.setRestaurantName(event.getNewValue());
                customHibernate.update(restaurant);
            }
        });

        // Cuisine type (only Restaurant, enum + combobox)
        cuisineTypeCol.setCellValueFactory(cellData ->
                cellData.getValue().cuisineTypeProperty()
        );
        cuisineTypeCol.setCellFactory(ComboBoxTableCell.forTableColumn(CuisineType.values()));
        cuisineTypeCol.setOnEditCommit(event -> {
            UserTableParameters row = event.getTableView().getItems()
                    .get(event.getTablePosition().getRow());
            row.setCuisineType(event.getNewValue());

            User user = customHibernate.getEntityById(
                    User.class,
                    row.getId()
            );
            if (user instanceof Restaurant restaurant) {
                restaurant.setCuisineType(event.getNewValue());
                customHibernate.update(restaurant);
            }
        });

        // Delivery fee (only Restaurant, editable double)
        deliveryFeeCol.setCellValueFactory(new PropertyValueFactory<>("deliveryFee"));
        deliveryFeeCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        deliveryFeeCol.setOnEditCommit(event -> {
            UserTableParameters row = event.getTableView().getItems()
                    .get(event.getTablePosition().getRow());
            row.setDeliveryFee(event.getNewValue());

            User user = customHibernate.getEntityById(
                    User.class,
                    row.getId()
            );
            if (user instanceof Restaurant restaurant) {
                restaurant.setDeliveryFee(event.getNewValue());
                customHibernate.update(restaurant);
            }
        });

        // Closing time (only Restaurant) – display only for now
        closingTimeClol.setCellValueFactory(new PropertyValueFactory<>("closingTime"));

        // Opening time (only Restaurant) – display only for now
        openingTimeCol.setCellValueFactory(new PropertyValueFactory<>("openingTime"));

        // Birth date (only Driver) – display only
        bDateCol.setCellValueFactory(new PropertyValueFactory<>("bDate"));

        // Vehicle type (only Driver, enum + combobox)
        vehicleTypeCol.setCellValueFactory(cellData ->
                cellData.getValue().vehicleTypeProperty()
        );
        vehicleTypeCol.setCellFactory(ComboBoxTableCell.forTableColumn(VehicleType.values()));
        vehicleTypeCol.setOnEditCommit(event -> {
            UserTableParameters row = event.getTableView().getItems()
                    .get(event.getTablePosition().getRow());
            row.setVehicleType(event.getNewValue());

            User user = customHibernate.getEntityById(
                    User.class,
                    row.getId()
            );
            if (user instanceof Driver driver) {
                driver.setVehicleType(event.getNewValue());
                customHibernate.update(driver);
            }
        });
        initActionColumn();
    }

    private void initActionColumn() {
        actionCol.setCellFactory(col -> new TableCell<UserTableParameters, Void>() {

            private final Button deleteBtn = new Button("Delete");

            {
                deleteBtn.setOnAction(event -> {
                    UserTableParameters row = getTableView().getItems().get(getIndex());
                    genericHibernate.delete(User.class, row.getId());
                    getTableView().getItems().remove(row);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : deleteBtn);
            }
        });
    }


    public void setData(EntityManagerFactory entityManagerFactory, User user) {
        this.entityManagerFactory = entityManagerFactory;
        this.currentUser = user;
        this.customHibernate = new CustomHibernate(entityManagerFactory);
        this.genericHibernate = new GenericHibernate(entityManagerFactory);
        setUserFormVisibility();
        reloadTableData();
    }

    private void setUserFormVisibility() {
        if (currentUser instanceof BasicUser){
            tabPane.getTabs().remove(userTab);
            tabPane.getTabs().remove(menuTab);
            updateOrderId.setDisable(true);
            deleteOrderID.setDisable(true);
        }
            else if (currentUser instanceof Restaurant){
                tabPane.getTabs().remove(userTab);
            }
        else if (currentUser instanceof Driver){
            tabPane.getTabs().remove(userTab);
            tabPane.getTabs().remove(menuTab);
            createOrderID.setDisable(true);
            updateOrderId.setDisable(true);
            deleteOrderID.setDisable(true);
        }else if (currentUser instanceof User) {
            //nebutina kazko cia idunno
        }
    }

    public void reloadTableData() {
        data.clear();
        userTable.getItems().clear();
        if (userTab.isSelected()) {
            List<User> users = customHibernate.getAllRecords(User.class);
            for (User u : users) {
                UserTableParameters p = new UserTableParameters();

                p.setId(u.getId());
                p.setUserType(u.getClass().getSimpleName());
                p.setLogin(u.getLogin());
                p.setPassword(u.getPassword());
                p.setName(u.getName());
                p.setSurname(u.getSurname());
                p.setPhone(u.getPhoneNumber());

                // BasicUser
                if (u instanceof BasicUser basicUser) {
                    p.setAddress(basicUser.getAddress());
                }

                // Driver
                if (u instanceof Driver driver) {
                    p.setAddress(driver.getAddress());
                    p.setBDate(driver.getbDate());
                    p.setVehicleType(driver.getVehicleType());
                }

                // Restaurant
                if (u instanceof Restaurant restaurant) {
                    p.setAddress(restaurant.getAddress());
                    p.setRestaurantName(restaurant.getRestaurantName());
                    p.setCuisineType(restaurant.getCuisineType());
                    p.setDeliveryFee(restaurant.getDeliveryFee());
                    p.setOpeningTime(restaurant.getOpeningTime());
                    p.setClosingTime(restaurant.getClosingTime());
                }

                data.add(p);
            }
            userTable.getItems().addAll(data);
        } else if (oerderTab.isSelected()) {
            clearAllOrderFields();
            List<FoodOrder> foodOrders = getFoodOrders();
            orderList.getItems().addAll(foodOrders);
            if (currentUser instanceof BasicUser basicUser) {
                clientList.getItems().add(basicUser);
                clientList.getSelectionModel().select(basicUser);
                clientList.setDisable(true);
            }
            else {
                clientList.getItems().addAll(customHibernate.getAllRecords(BasicUser.class));
                clientList.setDisable(false);
            }
            if (currentUser instanceof Restaurant restaurant) {
                restaurantField.getItems().add(restaurant);
                restaurantField.getSelectionModel().select(restaurant);
                restaurantField.setDisable(true);
            } else {
                restaurantField.getItems().addAll(customHibernate.getAllRecords(Restaurant.class));
                restaurantField.setDisable(false);
            }
            orderStatusField.getItems().addAll(OrderStatus.values());

        } else if (menuTab.isSelected()) {
            clearAllMenuFields();

            if (currentUser instanceof Restaurant restaurant) {
                restaurantList.getItems().add(restaurant);
                restaurantList.getSelectionModel().select(restaurant);
                restaurantList.setDisable(true);

                menuListField.getItems().addAll(
                        customHibernate.getRestourantCuisine(restaurant)
                );
            } else {
                restaurantList.getItems().addAll(
                        customHibernate.getAllRecords(Restaurant.class)
                );
                restaurantList.setDisable(false);
            }
        } else if (chatTab.isSelected()) {
                allChat.getItems().addAll(customHibernate.getAllRecords(Chat.class));

        }
    }

    private void clearAllOrderFields (){
        orderList.getItems().clear();
        clientList.getItems().clear();
        orderStatusField.getItems().clear();
        restaurantField.getItems().clear();
        titleField.clear();
        priceField.clear();
        restaurantList.getItems().clear();
        orderStatusField.getItems().clear();
    }

    private void clearAllMenuFields (){
        menuListField.getItems().clear();
        menuFoodNameField.clear();
        menuFoodNameField1.clear();
        restaurantField.getItems().clear();
        spicyCheckBox.setSelected(false);
        veganCheckBox.setSelected(false);
        restaurantList.getItems().clear();
    }

    public void openUserForm() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/budgetwolt2/user-form.fxml"));
        Parent parent = fxmlLoader.load();

        UserForm userForm = fxmlLoader.getController();
        userForm.setData(entityManagerFactory);
        Stage stage = new  Stage();
        Scene scene = new Scene(parent);
        stage.setTitle("Add new user");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

    }




    //Order Tab
    private List<FoodOrder> getFoodOrders (){
        if (currentUser instanceof Restaurant restaurant) {
            return customHibernate.getRestourantOrders(restaurant);
        }
        if (currentUser instanceof BasicUser basicUser) {
            List<FoodOrder> allOrders = customHibernate.getAllRecords(FoodOrder.class);
            return allOrders.stream()
                    .filter(o -> o.getBuyer() != null
                            && o.getBuyer().getId() == basicUser.getId())
                    .toList();
        }
        return customHibernate.getAllRecords(FoodOrder.class);
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public void createOrder() {
        if (isNumeric(priceField.getText())) {
            FoodOrder foodOrder = new FoodOrder(titleField.getText(), Double.parseDouble(priceField.getText()), clientList.getValue(),orderFoodList.getSelectionModel().getSelectedItems(), restaurantField.getValue(),  orderStatusField.getValue());
            customHibernate.create(foodOrder);
            reloadTableData();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Wrong value");
            alert.setContentText("Value in field PRICE should be a number");

            alert.showAndWait();
        }
    }

    public void updateOrder() {
        FoodOrder foodOrder = orderList.getSelectionModel().getSelectedItem();
        foodOrder.setRestaurant(restaurantField.getSelectionModel().getSelectedItem());
        foodOrder.setName(titleField.getText());
        foodOrder.setPrice(Double.valueOf(priceField.getText()));
        foodOrder.setOrderStatus(orderStatusField.getValue());
        foodOrder.setBuyer(clientList.getSelectionModel().getSelectedItem());

        customHibernate.update(foodOrder);
        reloadTableData();
    }

    public void deleteOrder() {
        FoodOrder selectedOrder = orderList.getSelectionModel().getSelectedItem();
        customHibernate.delete(FoodOrder.class, selectedOrder.getId());
        orderList.getItems().clear();
        orderList.getItems().addAll(customHibernate.getAllRecords(FoodOrder.class));
        reloadTableData();
    }

    public void loadOrderInfo() {
        FoodOrder selectedOrder = orderList.getSelectionModel().getSelectedItem();
        clientList.getItems().stream().filter(c->c.getId() == selectedOrder.getBuyer().getId()).findFirst().ifPresent(u->clientList.getSelectionModel().select(u));
        titleField.setText(selectedOrder.getName());
        priceField.setText(selectedOrder.getPrice().toString());
        restaurantField.getItems().stream().filter(r->r.getId() == selectedOrder.getRestaurant().getId()).findFirst().ifPresent(u->restaurantField.getSelectionModel().select(u));
        orderStatusField.getItems()
                .stream()
                .filter(r -> r == selectedOrder.getOrderStatus())
                .findFirst()
                .ifPresent(v -> orderStatusField.getSelectionModel().select(v));
       // disableFoodOrderFields();
    }

    private void disableFoodOrderFields () {
        if (orderStatusField.getSelectionModel().getSelectedItem() == OrderStatus.COMPLETED) {
            clientList.setDisable(true);
            priceField.setDisable(true);
            restaurantField.setDisable(true);
            titleField.setDisable(true);
        }
    }

    public void loadRestaurantMenuForOrder() {
        orderFoodList.getItems().clear();
        orderFoodList.getItems().addAll(customHibernate.getRestourantCuisine(restaurantField.getSelectionModel().getSelectedItem()));
    }

    public void filterOrders(ActionEvent actionEvent) {

    }

    public void createCuisine() {
        Cuisine cuisine = new Cuisine(menuFoodNameField.getText(), Double.parseDouble(menuFoodNameField1.getText()), spicyCheckBox.isSelected(), veganCheckBox.isSelected(), restaurantList.getSelectionModel().getSelectedItem());
        customHibernate.create(cuisine);
        reloadTableData();
    }

    public void updateCuisine() {
        Cuisine cuisine = menuListField.getSelectionModel().getSelectedItem();
        cuisine.setName(menuFoodNameField.getText());
        cuisine.setPrice(Double.valueOf(menuFoodNameField1.getText()));
        cuisine.setSpicy(spicyCheckBox.isSelected());
        cuisine.setVegan(veganCheckBox.isSelected());
        customHibernate.update(cuisine);
        reloadTableData();

    }

    public void deleteCuisine() {
        Cuisine selectedCuisine = menuListField.getSelectionModel().getSelectedItem();
        customHibernate.delete(Cuisine.class, selectedCuisine.getId());
        menuListField.getItems().clear();
        menuListField.getItems().addAll(customHibernate.getAllRecords(Cuisine.class));
        reloadTableData();

    }

    public void loadRestaurantMenu() {
        menuListField.getItems().clear();
        menuListField.getItems().addAll(customHibernate.getRestourantCuisine(restaurantList.getSelectionModel().getSelectedItem()));
    }


    public void loadMenuInfo() {
        Cuisine selectedMenu = menuListField.getSelectionModel().getSelectedItem();
        menuFoodNameField.setText(selectedMenu.getName());
        menuFoodNameField1.setText(selectedMenu.getPrice().toString());
        spicyCheckBox.setSelected(selectedMenu.isSpicy());
        veganCheckBox.setSelected(selectedMenu.isVegan());

    }

    public void loadChatMessages() {
        chtMessagesList.getItems().addAll((Review) customHibernate.getChatMessages(allChat.getSelectionModel().getSelectedItem()));
    }

    public void deleteChat() {
    }

    public void deleteMessage() {
    }

    public void loadChatForm() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/budgetwolt2/chat-form.fxml"));
        Parent parent = fxmlLoader.load();
        UserForm userForm = fxmlLoader.getController();
        userForm.setData(entityManagerFactory);
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setTitle("Chat");
        stage.setScene(scene);
        stage.show();
    }
}
