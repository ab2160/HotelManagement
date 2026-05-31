package com.mycompany.guestController;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class guest4Controller {
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
    
    Parent root;
    Stage stage;

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
    public void cancelService(ActionEvent event) {
        Service selectedService = serviceTable.getSelectionModel().getSelectedItem();

        if (selectedService == null) {
            System.out.println("Please select a service to cancel.");
            return;
        }

        try {
            int bookingid = selectedService.getBookingId();
            int serviceid = selectedService.getServiceid();
            ServiceConnection SC = new ServiceConnection();
            boolean success = SC.cancelGuestService(bookingid, serviceid);
            if(success)
            {
                System.out.println("Service cancelled successfully.");
            }
            else
            {
                System.out.println("Service not cancelled.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        @FXML
    public void switchToguest1(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/guestFxml/guest1.fxml"));
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
    public void switchToguest3(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guestFxml/guest3.fxml"));
            root = loader.load();
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
