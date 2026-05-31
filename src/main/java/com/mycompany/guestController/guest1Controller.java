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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
        if (selectedRoom == null || checkin == null) {
            System.out.println("Please select a check in date and room.");
            return;
        }

        try {
            java.util.Date Checkin = java.util.Date.from(
                    checkin.atStartOfDay(ZoneId.systemDefault()).toInstant()
            );

            Booking B = new Booking();
            B.setCheckin(Checkin);
            B.setRoomnum(selectedRoom.getRoomnum());
            BookingConnection BC = new BookingConnection();
            boolean success = BC.addBooking(B);
            if(success)
            {
                System.out.println("Booking added successfully!");
                System.out.println("Selected room: " + selectedRoom.getRoomnum());
            }
            else
            {
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

            stage.close();
            Stage homeStage = new Stage();
            homeStage.setScene(scene);
            homeStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void switchToguest2(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/guestFxml/guest2.fxml"));
            Scene scene = new Scene(root);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

            String css = getClass().getResource("/CSS/Style.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void switchToguest4(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/guestFxml/guest4.fxml"));
            Scene scene = new Scene(root);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

            String css = getClass().getResource("/CSS/Style.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        @FXML
    public void switchToguest5(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/guestFxml/guest5.fxml"));
            Scene scene = new Scene(root);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

            String css = getClass().getResource("/CSS/Style.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
