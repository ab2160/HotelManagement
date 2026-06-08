package com.mycompany.managerController;

import com.mycompany.dao.BookingConnection;
import com.mycompany.dao.DatabaseConnection;
import com.mycompany.model.AvailableInfo;
import com.mycompany.model.Booking;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class removeBookingandCheckoutController {

    @FXML
    private TableView<AvailableInfo> infoTable;
    @FXML
    private TableColumn<AvailableInfo, Integer> colBookingId;
    @FXML
    private TableColumn<AvailableInfo, Integer> colRoomNum;
    @FXML
    private TableColumn<AvailableInfo, String> colRoomStatus;
    @FXML
    private TableColumn<AvailableInfo, Double> colTotalAmount;
    Parent root;
    Stage stage;

    public void initialize() {
        colBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colRoomNum.setCellValueFactory(new PropertyValueFactory<>("roomNum"));
        colRoomStatus.setCellValueFactory(new PropertyValueFactory<>("roomStatus"));
        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        ObservableList<AvailableInfo> info = FXCollections.observableArrayList();

        try (Connection con = DatabaseConnection.getConnection()) {
            String available = "SELECT "
                    + "b.booking_id, "
                    + "r.room_num, "
                    + "r.room_status, "
                    + "p.total_amount "
                    + "FROM Booking b "
                    + "JOIN Room r ON b.room_num = r.room_num "
                    + "JOIN Payment p ON b.booking_id = p.booking_id";

            PreparedStatement P = con.prepareStatement(available);
            ResultSet rs = P.executeQuery();

            while (rs.next()) {
                AvailableInfo information = new AvailableInfo(
                        rs.getInt("booking_id"),
                        rs.getInt("room_num"),
                        rs.getString("room_status"),
                        rs.getDouble("total_amount")
                );
                info.add(information);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        infoTable.setItems(info);
    }

    @FXML
    public void removeBooking(ActionEvent event)
    {
        AvailableInfo selectedBooking = infoTable.getSelectionModel().getSelectedItem();
        
        if (selectedBooking == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No booking Service");
            alert.setContentText("Please select a booking to remove.");
            alert.showAndWait();
            System.out.println("Please select a booking to remove.");
            return;
        }
        
        try {
            int bookingId = selectedBooking.getBookingId();
            int roomNum = selectedBooking.getRoomNum();
            BookingConnection BC = new BookingConnection();
            boolean success = BC.removeBooking(bookingId, roomNum);
            if (success) {
                System.out.println("Booking removed successfully.");
                infoTable.getItems().remove(selectedBooking);
            } else {
                System.out.println("Booking not removed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void checkOut(ActionEvent event)
    {
        AvailableInfo selectedPayment = infoTable.getSelectionModel().getSelectedItem();
        if (selectedPayment != null) {
            int bookingId = selectedPayment.getBookingId();

            Booking selectedBooking = null;
            try (Connection con = DatabaseConnection.getConnection()) {
                PreparedStatement P = con.prepareStatement("SELECT booking_id, checkin_date, room_num FROM Booking WHERE booking_id = ?");
                P.setInt(1, bookingId);
                ResultSet r = P.executeQuery();
                if (r.next()) {
                    selectedBooking = new Booking(r.getInt("booking_id"),
                            r.getDate("checkin_date"),
                            r.getInt("room_num"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (selectedBooking != null) {
                BookingConnection BC = new BookingConnection();
                boolean success = BC.check_out(selectedBooking);
                if (success) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Completed");
                    alert.setHeaderText("Check out successful.");
                    alert.setContentText("Total amount paid: " + selectedPayment.getTotalAmount());
                    alert.showAndWait();
                    System.out.println("Check out successful.");
                    infoTable.getItems().remove(selectedPayment);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Failed");
                    alert.setContentText("Check out failed.");
                    System.out.println("Check out failed.");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No payment found");
                System.out.println("No payment found for Booking id: " + bookingId);
            }
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
}
