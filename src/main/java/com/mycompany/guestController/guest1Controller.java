package com.mycompany.guestController;

import com.mycompany.dao.BookingConnection;
import com.mycompany.dao.DatabaseConnection;
import com.mycompany.model.Booking;
import com.mycompany.model.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class guest1Controller {

    Parent root;
    Stage stage;
    @FXML
    private Label welcomelabel;
    @FXML
    private DatePicker checkin_date;
    @FXML
    private TableView<Room> roomTable;
    @FXML
    private TableColumn<Room, Integer> colRoomNum;
    @FXML
    private TableColumn<Room, String> colRoomStatus;
    @FXML
    private TableColumn<Room, String> colClassName;
    @FXML
    private TableColumn<Room, String> colBedType;
    @FXML
    private TableColumn<Room, Double> colRoomPrice;

    @FXML
    public void displayGuest(String name) {
        welcomelabel.setText("Welcome Guest " + name);
    }

    private int currentGuestId;

    public void setCurrentGuestId(int guestId) {
        this.currentGuestId = guestId;
    }

    @FXML
    public void initialize() {
        colRoomNum.setCellValueFactory(new PropertyValueFactory<>("roomnum"));
        colRoomStatus.setCellValueFactory(new PropertyValueFactory<>("roomstatus"));
        colClassName.setCellValueFactory(new PropertyValueFactory<>("classname"));
        colBedType.setCellValueFactory(new PropertyValueFactory<>("bedtype"));
        colRoomPrice.setCellValueFactory(new PropertyValueFactory<>("roomprice"));

        ObservableList<Room> availableRooms = FXCollections.observableArrayList();
        try (Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement P = con.prepareStatement("SELECT * FROM Room WHERE room_status='Available'");
            ResultSet r = P.executeQuery();
            while (r.next()) {
                Room room = new Room(
                        r.getInt("room_num"),
                        r.getString("room_status"),
                        r.getString("class_name"),
                        r.getString("bed_type"),
                        r.getDouble("room_price")
                );
                availableRooms.add(room);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        roomTable.setItems(availableRooms);
    }

    @FXML
    public void addBooking(ActionEvent event) {
        Room selectedRoom = roomTable.getSelectionModel().getSelectedItem();
        LocalDate checkin = checkin_date.getValue();
        if (checkin == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Missing information");
            alert.setHeaderText("Booking not specified.");
            alert.setContentText("Please select a check in date.");
            alert.showAndWait();
            System.out.println("Please select a check in date.");
            return;
        }

        if (selectedRoom == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Missing information");
            alert.setHeaderText("Room not selected.");
            alert.setContentText("Please select a room available in the table.");
            alert.showAndWait();
            System.out.println("Please select a room available in the table.");
            return;
        }
        if (checkin.isBefore(LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Date");
            alert.setHeaderText("Invalid Check-in Date");
            alert.setContentText("Check-in date cannot be in the past.");
            alert.showAndWait();
            return;
        }

        try {
            java.util.Date Checkin = java.util.Date.from(
                    checkin.atStartOfDay(ZoneId.systemDefault()).toInstant()
            );

            Booking B = new Booking();
            B.setCheckin(Checkin);
            B.setRoomnum(selectedRoom.getRoomnum());
            B.setGuestId(currentGuestId);
            BookingConnection BC = new BookingConnection();
            boolean success = BC.addBooking(B);
            if (success) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Completed");
                alert.setHeaderText("Booking added successfully.");
                alert.setContentText("Selected room: " + selectedRoom.getRoomnum());
                alert.showAndWait();
                System.out.println("Booking added successfully!");
                System.out.println("Selected room: " + selectedRoom.getRoomnum());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failed");
                alert.setContentText("Booking failed!");
                System.out.println("Booking failed!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchTohome(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/guestFxml/homePage.fxml"));
            Scene scene = new Scene(root);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

            String css = getClass().getResource("/CSS/Style.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Hotel.png")));
            stage.setTitle("Hotel Management System");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToguest2(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guestFxml/guest2.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);

            guest2Controller bookingController = loader.getController();
            bookingController.setCurrentGuestId(currentGuestId);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

            String css = getClass().getResource("/CSS/Style.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Hotel.png")));
            stage.setTitle("Hotel Management System");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToguest4(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guestFxml/guest4.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);

            guest4Controller bookingController = loader.getController();
            bookingController.setCurrentGuestId(currentGuestId);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

            String css = getClass().getResource("/CSS/Style.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Hotel.png")));
            stage.setTitle("Hotel Management System");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToguest5(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guestFxml/guest5.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);

            guest5Controller bookingController = loader.getController();
            bookingController.setCurrentGuestId(currentGuestId);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

            String css = getClass().getResource("/CSS/Style.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Hotel.png")));
            stage.setTitle("Hotel Management System");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
