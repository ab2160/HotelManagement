package com.mycompany.waiterController;

import com.mycompany.dao.DatabaseConnection;
import com.mycompany.dao.ServiceConnection;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class waiter1Controller {

    @FXML
    private Label welcomelabel;
    Parent root;
    Stage stage;

    @FXML
    public void displayWaiter(String name) {
        welcomelabel.setText("Welcome Waiter " + name);
    }

    @FXML
    private TableView<Service> serviceTable;
    @FXML
    private TableColumn<Service, Integer> colServiceId;
    @FXML
    private TableColumn<Service, String> colServiceType;
    @FXML
    private TableColumn<Service, Double> colServicePrice;
    @FXML
    private TableColumn<Service, String> colServiceStatus;
    @FXML
    private TableColumn<Service, Integer> colBookingId;

    @FXML
    public void initialize() {
        colServiceId.setCellValueFactory(new PropertyValueFactory<>("serviceid"));
        colServiceType.setCellValueFactory(new PropertyValueFactory<>("servicetype"));
        colServicePrice.setCellValueFactory(new PropertyValueFactory<>("servicePrice"));
        colServiceStatus.setCellValueFactory(new PropertyValueFactory<>("serviceStatus"));
        colBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));

        ObservableList<Service> availableServices = FXCollections.observableArrayList();
        try (Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement P = con.prepareStatement(
                    "SELECT s.service_id, s.service_type, s.service_price, sr.service_status, sr.booking_id "
                    + "FROM ServiceAdding s "
                    + "JOIN Service sr ON s.service_id = sr.service_id;"
            );

            ResultSet r = P.executeQuery();
            while (r.next()) {
                Service ser = new Service(
                        r.getInt("service_id"),
                        r.getString("service_type"),
                        r.getDouble("service_price"),
                        r.getString("service_status"),
                        r.getInt("booking_id")
                );
                availableServices.add(ser);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        serviceTable.setItems(availableServices);
    }

    @FXML
    public void completeService(ActionEvent event) {
        Service selectedService = serviceTable.getSelectionModel().getSelectedItem();
        if (selectedService == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selected Service");
            alert.setContentText("Please select a service to complete.");
            alert.showAndWait();
            return;
        }
        ServiceConnection SC = new ServiceConnection();
        int serviceid = selectedService.getServiceid();
        int bookingid = selectedService.getBookingId();
        boolean success = SC.completeService(serviceid, bookingid);
        if (success) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Completed");
            alert.setContentText("Service completed successfully.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not Completed");
            alert.setContentText("Service not completed successfully.");
            alert.showAndWait();
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
            homeStage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Hotel.png")));
            homeStage.setTitle("Hotel Management System");
            homeStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
