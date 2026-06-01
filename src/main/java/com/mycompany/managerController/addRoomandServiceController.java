package com.mycompany.managerController;

import com.mycompany.dao.RoomConnection;
import com.mycompany.dao.ServiceConnection;
import com.mycompany.model.Room;
import com.mycompany.model.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class addRoomandServiceController {

    @FXML
    private TextField roomstatusField;
    @FXML
    private TextField classnameField;
    @FXML
    private TextField bedtypeField;
    @FXML
    private TextField roompriceField;
    @FXML
    private TextField servicetypeField;
    @FXML
    private TextField servicepriceField;

    Stage stage;
    Parent root;

    @FXML
    public void initialize() {
        roomstatusField.setOnKeyPressed((KeyEvent t) -> {
            if (t.getCode() == KeyCode.DOWN) {
                classnameField.requestFocus();
            }
        });
        classnameField.setOnKeyPressed((KeyEvent t) -> {
            if (t.getCode() == KeyCode.UP) {
                roomstatusField.requestFocus();
            } else if (t.getCode() == KeyCode.DOWN) {
                bedtypeField.requestFocus();
            } else if (t.getCode() == KeyCode.RIGHT) {
                bedtypeField.requestFocus();
            }
        });
        bedtypeField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                classnameField.requestFocus();
            } else if (e.getCode() == KeyCode.DOWN) {
                roompriceField.requestFocus();
            }
        });
        roompriceField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                bedtypeField.requestFocus();
            } else if (e.getCode() == KeyCode.LEFT) {
                roomstatusField.requestFocus();
            }
        });

        servicetypeField.setOnKeyPressed(t -> {
            if (t.getCode() == KeyCode.DOWN) {
                servicepriceField.requestFocus();
            }
        });

        servicepriceField.setOnKeyPressed(t -> {
            if (t.getCode() == KeyCode.UP) {
                servicetypeField.requestFocus();
            }
        });
    }

    @FXML
    public void addRoom(ActionEvent event) {
        try {
            String rstatus = roomstatusField.getText();
            String cname = classnameField.getText();
            String bed = bedtypeField.getText();
            double price = Double.parseDouble(roompriceField.getText());

            int roomnum = 0;
            Room R = new Room(roomnum, rstatus, cname, bed, price);
            RoomConnection RC = new RoomConnection();
            boolean success = RC.addRoom(R);
            if (success) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Room adding");
                alert.setHeaderText("Room added successfully.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Room adding");
                alert.setHeaderText("Room adding failed.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addService(ActionEvent event) {
        try {
            String stype = servicetypeField.getText();
            double sprice = Double.parseDouble(servicepriceField.getText());

            int serviceid = 0;
            Service S = new Service(serviceid, stype, sprice);
            ServiceConnection SC = new ServiceConnection();
            boolean success = SC.addService(S);
            if (success) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Service adding");
                alert.setHeaderText("Service added successfully.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Service adding");
                alert.setHeaderText("Service adding failed.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchTomanager1(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/managerFxml/manager1.fxml"));
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

}
