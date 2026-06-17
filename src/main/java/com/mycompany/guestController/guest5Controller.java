package com.mycompany.guestController;

import com.mycompany.dao.BookingConnection;
import com.mycompany.dao.DatabaseConnection;
import com.mycompany.model.Booking;
import com.mycompany.model.Payment;
import com.mycompany.model.Room;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class guest5Controller {

    @FXML
    ChoiceBox<Booking> bookingChoiceBox;
    @FXML
    ChoiceBox<Room> roomChoiceBox;
    @FXML
    TableView<Payment> paymentTable;
    @FXML
    TableColumn<Payment, Integer> colPaymentId;
    @FXML
    TableColumn<Payment, Integer> colBookingId;
    @FXML
    TableColumn<Payment, Double> colTotalAmount;
    @FXML
    TableColumn<Payment, Date> colPaymentDate;
    @FXML
    TableColumn<Payment, String> colPaymentStatus;

    Parent root;
    Stage stage;

    private int currentGuestId;

    public void setCurrentGuestId(int guestId) {
        this.currentGuestId = guestId;
        initialize();
    }

    @FXML
    public void initialize() {
        ObservableList<Booking> availableBooking = FXCollections.observableArrayList();
        ObservableList<Room> reservedRoom = FXCollections.observableArrayList();
        try (Connection con = DatabaseConnection.getConnection()) {
            String availableBook = "SELECT booking_id, checkin_date, room_num FROM Booking";
            PreparedStatement P1 = con.prepareStatement(availableBook);
            ResultSet r1 = P1.executeQuery();
            while (r1.next()) {
                Booking book = new Booking(
                        r1.getInt("booking_id"),
                        r1.getDate("checkin_date"),
                        r1.getInt("room_num")
                );
                availableBooking.add(book);
            }

            String reservedRooms = "SELECT r.room_num, r.room_price "
                    + "FROM Room r "
                    + "JOIN Booking b ON r.room_num = b.room_num";

            PreparedStatement P2 = con.prepareStatement(reservedRooms);
            ResultSet r2 = P2.executeQuery();
            while (r2.next()) {
                Room room = new Room(
                        r2.getInt("room_num"),
                        r2.getDouble("room_price")
                );
                reservedRoom.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        bookingChoiceBox.setItems(availableBooking);
        roomChoiceBox.setItems(reservedRoom);

        bookingChoiceBox.setConverter(new StringConverter<Booking>() {
            @Override
            public String toString(Booking t) {
                if (t == null) {
                    return "";
                } else {
                    return "Booking id: " + t.getBookingid() + " " + "Room number: " + t.getRoomnum();
                }
            }

            @Override
            public Booking fromString(String string) {
                return null;
            }
        });

        roomChoiceBox.setConverter(new StringConverter<Room>() {
            @Override
            public String toString(Room t) {
                if (t == null) {
                    return "";
                } else {
                    return "Room number: " + t.getRoomnum() + " " + "Room price: " + t.getRoomprice();
                }
            }

            @Override
            public Room fromString(String string) {
                return null;
            }
        });

        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colBookingId.setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        colPaymentStatus.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));

        ObservableList<Payment> availablePayment = FXCollections.observableArrayList();
        try (Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement P = con.prepareStatement(
                    "SELECT p.payment_id, p.booking_id, p.total_amount, p.payment_date, p.payment_status "
                    + "FROM Payment p "
                    + "JOIN Booking b ON p.booking_id = b.booking_id "
                    + "JOIN Guest_booking gb ON gb.booking_id = b.booking_id "
                    + "JOIN Guest g ON g.guest_id = gb.guest_id "
                    + "WHERE g.guest_id = ?"
            );

            P.setInt(1, currentGuestId);
            ResultSet R = P.executeQuery();
            while (R.next()) {
                Payment pay = new Payment(
                        R.getInt("payment_id"),
                        R.getInt("booking_id"),
                        R.getDouble("total_amount"),
                        R.getDate("payment_date"),
                        R.getString("payment_status"));
                availablePayment.add(pay);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        paymentTable.setItems(availablePayment);
    }

    @FXML
    public void checkOut(ActionEvent event) {
        Payment selectedPayment = paymentTable.getSelectionModel().getSelectedItem();
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
                    paymentTable.getItems().remove(selectedPayment);
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
    public void switchToguest1(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guestFxml/guest1.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);

            guest1Controller welcomeController = loader.getController();

            try (Connection con = DatabaseConnection.getConnection()) {
                PreparedStatement P = con.prepareStatement(
                        "SELECT u.f_name, g.guest_id "
                        + "FROM Guest g "
                        + "JOIN User u ON g.user_name = u.user_name "
                        + "WHERE g.guest_id = ?"
                );
                P.setInt(1, currentGuestId);
                ResultSet r = P.executeQuery();
                if (r.next()) {
                    String fname = r.getString("f_name");
                    welcomeController.displayGuest(fname);
                    welcomeController.setCurrentGuestId(currentGuestId);
                }
            } catch (Exception e) {
                e.printStackTrace();
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
    public void switchToguest3(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/guestFxml/guest3.fxml"));
            root = loader.load();
            guest3Controller bookingController = loader.getController();
            bookingController.setCurrentGuestId(currentGuestId);
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
}
