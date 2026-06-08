package com.mycompany.managerController;

import com.mycompany.dao.DatabaseConnection;
import com.mycompany.dao.RoomConnection;
import com.mycompany.dao.ServiceConnection;
import com.mycompany.model.Room;
import com.mycompany.model.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class removeRoomandServiceController {

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
    private TableView<Service> serviceTable;
    @FXML
    private TableColumn<Service, Integer> colserviceid;
    @FXML
    private TableColumn<Service, String> colservicetype;
    @FXML
    private TableColumn<Service, Double> colserviceprice;
    Parent root;
    Stage stage;

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

        colserviceid.setCellValueFactory(new PropertyValueFactory<>("serviceid"));
        colservicetype.setCellValueFactory(new PropertyValueFactory<>("servicetype"));
        colserviceprice.setCellValueFactory(new PropertyValueFactory<>("servicePrice"));

        ObservableList<Service> availableServices = FXCollections.observableArrayList();
        try (Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement P = con.prepareStatement("SELECT * FROM ServiceAdding;");
            ResultSet r = P.executeQuery();
            while (r.next()) {
                Service S = new Service(
                        r.getInt("service_id"),
                        r.getString("service_type"),
                        r.getDouble("service_price"));
                availableServices.add(S);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        serviceTable.setItems(availableServices);
    }

    @FXML
    public void removeRoom(ActionEvent event) {
        Room selectedRoom = roomTable.getSelectionModel().getSelectedItem();
        if (selectedRoom == null) {
            System.out.println("Please select a room to remove.");
            return;
        }
        
        try {
            int roomNum = selectedRoom.getRoomnum();
            RoomConnection RC = new RoomConnection();
            boolean success = RC.removeRoom(roomNum);
            
            if (success) {
                System.out.println("Room removed successfully.");
                roomTable.getItems().remove(selectedRoom);
            } else {
                System.out.println("Room not removed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void removeService(ActionEvent event) {
        Service selectedService = serviceTable.getSelectionModel().getSelectedItem();
        if (selectedService == null) {
            System.out.println("Please select a service to remove.");
            return;
        }
        try {
            ServiceConnection SC = new ServiceConnection();
            boolean success = SC.removeService(selectedService);
            
            if (success) {
                System.out.println("Service removed successfully.");
                serviceTable.getItems().remove(selectedService);
            } else {
                System.out.println("Service not removed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void switchToNewmanager(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/managerFxml/newManagerFxml.fxml"));
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
    public void switchToWaiter1(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/waiterFxml/waiter1.fxml"));
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
