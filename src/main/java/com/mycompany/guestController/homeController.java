package com.mycompany.guestController;

import com.mycompany.dao.DatabaseConnection;
import com.mycompany.model.Room;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class homeController {

    @FXML
    private MenuItem restoreItem;
    @FXML
    private MenuItem maximizeItem;
    @FXML
    private MenuItem minimizeItem;
    @FXML
    private MenuItem closeItem;
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

    Stage stage;
    Parent root;

    @FXML
    public void initialize() {
        restoreItem.setOnAction(e -> {
            stage = (Stage) restoreItem.getParentPopup().getOwnerWindow();
            stage.setMaximized(false);
        });

        maximizeItem.setOnAction(e -> {
            stage = (Stage) maximizeItem.getParentPopup().getOwnerWindow();
            stage.setMaximized(true);
        });

        minimizeItem.setOnAction(e -> {
            stage = (Stage) minimizeItem.getParentPopup().getOwnerWindow();
            stage.setIconified(true);
        });

        closeItem.setOnAction(e -> {
            stage = (Stage) closeItem.getParentPopup().getOwnerWindow();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit Confirmation");
            alert.setHeaderText("Are you sure you want to exit?");

            ButtonType yesbtn = new ButtonType("YES", ButtonBar.ButtonData.YES);
            ButtonType nobtn = new ButtonType("NO", ButtonBar.ButtonData.NO);

            alert.getButtonTypes().setAll(yesbtn, nobtn);
            alert.showAndWait().ifPresent(response -> {
                if (response == yesbtn) {
                    stage.close();
                }
            });
        });
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
    public void switchToregister(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/guestFxml/registerScene.fxml"));
            Scene scene = new Scene(root);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            String css = getClass().getResource("/CSS/Style.css").toExternalForm();
            scene.getStylesheets().add(css);

            Stage newStage = new Stage();
            newStage.initStyle(StageStyle.UNDECORATED);

            newStage.setScene(scene);
            newStage.show();
            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchTologin(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/guestFxml/loginScene.fxml"));
            Scene scene = new Scene(root);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            String css = getClass().getResource("/CSS/Style.css").toExternalForm();
            scene.getStylesheets().add(css);

            Stage newStage = new Stage();
            newStage.initStyle(StageStyle.UNDECORATED);

            newStage.setScene(scene);
            newStage.show();
            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
