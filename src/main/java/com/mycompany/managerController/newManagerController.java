package com.mycompany.managerController;

import com.mycompany.dao.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import com.mycompany.model.AvailableInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class newManagerController {

    @FXML
    private BorderPane rootPane;
    @FXML
    private HBox headerPane;
    @FXML
    private AnchorPane mainContent;
    @FXML
    private Label welcomelabel;
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

    @FXML
    public void displayManager(String name) {
        welcomelabel.setText("Welcome Manager " + name);
    }

    @FXML
    public void initialize() {
        rootPane.setLeft(null);

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
    private VBox sideBar;

    @FXML
    private void toggleSidebar() {
        if (rootPane.getLeft() == null) {
            rootPane.setLeft(sideBar);
        } else {
            rootPane.setLeft(null);
        }
    }

        @FXML
    public void switchTohome(ActionEvent event) {
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
    public void switchToWaiterregister(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/waiterFxml/registerWaiter.fxml"));
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
    public void switchToManagerregister(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/managerFxml/registerManager.fxml"));
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
    public void switchToremoveUser(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/managerFxml/removeUser.fxml"));
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
    public void switchToaddRoomandService(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/managerFxml/addRoomandService.fxml"));
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
    
    @FXML
    public void switchToremoveRoomandService(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/managerFxml/removeRoomandService.fxml"));
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
    public void switchToguestSidebar(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/managerFxml/guestSidebar.fxml"));
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
    public void switchTostaffSidebar(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/managerFxml/staffSidebar.fxml"));
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
    public void switchToremoveBookingandCheckout(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/managerFxml/removeBookingandCheckout.fxml"));
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
