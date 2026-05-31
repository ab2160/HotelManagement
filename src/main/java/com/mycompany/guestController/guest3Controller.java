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
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class guest3Controller {

    @FXML
    ChoiceBox<Service> serviceChoiceBox;
    Parent root;
    Stage stage;

    @FXML
    public void initialize() {
        ObservableList<Service> availableServices = FXCollections.observableArrayList();
        try (Connection con = DatabaseConnection.getConnection()) {
            String available = "SELECT * FROM ServiceAdding;";
            PreparedStatement P = con.prepareCall(available);
            ResultSet r = P.executeQuery();
            while (r.next()) {
                Service ser = new Service(
                        r.getInt("service_id"),
                        r.getString("Service_type"),
                        r.getDouble("service_price")
                );
                availableServices.add(ser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        serviceChoiceBox.setItems(availableServices);

        serviceChoiceBox.setConverter(new StringConverter<Service>() {
            @Override
            public String toString(Service t) {
                if (t == null) {
                    return "";
                } else {
                    return t.getServicetype() + " " + t.getServicePrice();
                }
            }

            @Override
            public Service fromString(String string) {
                return null;
            }
        });
    }
    private int currentBookingId;

    public void setCurrentBookingId(int bookingId) {
        this.currentBookingId = bookingId;
    }

    @FXML
    public void addService(ActionEvent event) {
        Service selectedService = serviceChoiceBox.getSelectionModel().getSelectedItem();

        if (selectedService == null) {
            System.out.println("Please select a service to add.");
        }
        try {
            int serviceId = selectedService.getServiceid();
            int bookingId = currentBookingId;
            ServiceConnection SC = new ServiceConnection();
            boolean success = SC.addGuestService(bookingId, serviceId);
            if (success) {
                System.out.println("Service added successfully.");
            } else {
                System.out.println("Service not added.");
            }
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
    

}
