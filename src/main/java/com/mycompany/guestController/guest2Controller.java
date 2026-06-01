package com.mycompany.guestController;

import com.mycompany.dao.BookingConnection;
import com.mycompany.dao.DatabaseConnection;
import com.mycompany.model.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class guest2Controller {

    @FXML
    private TableView<Booking> bookingTable;
    @FXML
    private TableColumn<Booking, Integer> colBookingid;
    @FXML
    private TableColumn<Booking, Date> colCheckindate;
    @FXML
    private TableColumn<Booking, Integer> colRoomnum;
    Parent root;
    Stage stage;

    @FXML
    public void initialize() {
        colBookingid.setCellValueFactory(new PropertyValueFactory<>("bookingid"));
        colCheckindate.setCellValueFactory(new PropertyValueFactory<>("checkin"));
        colRoomnum.setCellValueFactory(new PropertyValueFactory<>("roomnum"));

        ObservableList<Booking> reservedBookings = FXCollections.observableArrayList();
        try (Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement P = con.prepareStatement("SELECT booking_id, checkin_date, room_num FROM Booking;");
            ResultSet r = P.executeQuery();
            while (r.next()) {
                Booking book = new Booking(
                        r.getInt("booking_id"),
                        r.getDate("checkin_date"),
                        r.getInt("room_num")
                );
                reservedBookings.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        bookingTable.setItems(reservedBookings);
    }

    @FXML
    public void removeBooking(ActionEvent event) {
        Booking selectedBooking = bookingTable.getSelectionModel().getSelectedItem();
        if (selectedBooking == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No booking Service");
            alert.setContentText("Please select a booking to remove.");
            alert.showAndWait();
            System.out.println("Please select a booking to remove.");
            return;
        }

        try {
            int bookingId = selectedBooking.getBookingid();
            int roomNum = selectedBooking.getRoomnum();
            BookingConnection BC = new BookingConnection();
            boolean success = BC.removeBooking(bookingId, roomNum);
            if (success) {
                System.out.println("Booking removed successfully.");
                bookingTable.getItems().remove(selectedBooking);
            } else {
                System.out.println("Booking not removed.");
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

            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Hotel.png")));
            stage.setTitle("Hotel Management System");
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
            Booking selectedBooking = bookingTable.getSelectionModel().getSelectedItem();
            if (selectedBooking == null) {
                System.out.println("Please select a booking first.");
                return;
            } else if (selectedBooking != null) {
                guest3Controller controller = loader.getController();
                controller.setCurrentBookingId(selectedBooking.getBookingid());
            }
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
            root = FXMLLoader.load(getClass().getResource("/guestFxml/guest4.fxml"));
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
    public void switchToguest5(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/guestFxml/guest5.fxml"));
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
