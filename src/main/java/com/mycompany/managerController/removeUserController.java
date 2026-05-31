package com.mycompany.managerController;

import com.mycompany.dao.DatabaseConnection;
import com.mycompany.dao.ManagerConnection;
import com.mycompany.model.Guest;
import com.mycompany.model.Manager;
import com.mycompany.model.Waiter;
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

public class removeUserController {

    @FXML
    TableView<Guest> guestTable;
    @FXML
    TableColumn<Guest, Integer> colGuestid;
    @FXML
    TableColumn<Guest, String> colUsername;

    @FXML
    TableView<Manager> managerTable;
    @FXML
    TableColumn<Manager, Integer> colManagerid;
    @FXML
    TableColumn<Manager, String> colUsername1;

    @FXML
    TableView<Waiter> waiterTable;
    @FXML
    TableColumn<Waiter, Integer> colWaiterid;
    @FXML
    TableColumn<Waiter, String> colUsername2;

    Parent root;
    Stage stage;

    @FXML
    public void initialize() {
        colGuestid.setCellValueFactory(new PropertyValueFactory<>("guestid"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));

        ObservableList<Guest> registeredGuest = FXCollections.observableArrayList();
        try (Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement P = con.prepareStatement("SELECT guest_id, user_name FROM Guest;");
            ResultSet r = P.executeQuery();
            while (r.next()) {
                Guest g = new Guest(r.getInt("guest_id"), r.getString("user_name"));
                registeredGuest.add(g);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        guestTable.setItems(registeredGuest);

        colManagerid.setCellValueFactory(new PropertyValueFactory<>("managerid"));
        colUsername1.setCellValueFactory(new PropertyValueFactory<>("username"));

        ObservableList<Manager> registeredManager = FXCollections.observableArrayList();
        try (Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement P = con.prepareStatement("SELECT manager_id, user_name FROM Manager;");
            ResultSet r = P.executeQuery();
            while (r.next()) {
                Manager M = new Manager(r.getInt("manager_id"), r.getString("user_name"));
                registeredManager.add(M);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        managerTable.setItems(registeredManager);

        colWaiterid.setCellValueFactory(new PropertyValueFactory<>("waiterid"));
        colUsername2.setCellValueFactory(new PropertyValueFactory<>("username"));

        ObservableList<Waiter> registeredWaiter = FXCollections.observableArrayList();
        try (Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement P = con.prepareStatement("SELECT waiter_id, user_name FROM Waiter;");
            ResultSet r = P.executeQuery();
            while (r.next()) {
                Waiter W = new Waiter(r.getInt("waiter_id"), r.getString("user_name"));
                registeredWaiter.add(W);
            }
            waiterTable.setItems(registeredWaiter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void removeGuest(ActionEvent event) {
        Guest selectedGuest = guestTable.getSelectionModel().getSelectedItem();
        if (selectedGuest == null) {
            System.out.println("Please select a guest to remove.");
            return;
        }

        try {
            String username = selectedGuest.getUsername();
            Guest G = new Guest();
            ManagerConnection MC = new ManagerConnection();
            boolean success = MC.DeleteGuestData(username);

            if (success) {
                System.out.println("Guest removed successfully.");
                guestTable.getItems().remove(selectedGuest);
            } else {
                System.out.println("Guest not removed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void removeManager(ActionEvent event) {
        Manager selectedManager = managerTable.getSelectionModel().getSelectedItem();
        if (selectedManager == null) {
            System.out.println("Please select a manager to remove.");
            return;
        }

        try {
            String username = selectedManager.getUsername();
            Manager M = new Manager();
            ManagerConnection MC = new ManagerConnection();
            boolean success = MC.DeleteManagerData(username);

            if (success) {
                System.out.println("Manager removed successfully.");
                managerTable.getItems().remove(selectedManager);
            } else {
                System.out.println("Manager not removed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void removeWaiter(ActionEvent event) {
        Waiter selectedWaiter = waiterTable.getSelectionModel().getSelectedItem();
        if (selectedWaiter == null) {
            System.out.println("Please select a waiter to remove.");
            return;
        }
        try {
            String username = selectedWaiter.getUsername();
            Waiter W = new Waiter();
            ManagerConnection MC = new ManagerConnection();
            boolean success = MC.DeleteWaiterData(username);

            if (success) {
                System.out.println("Waiter removed successfully.");
                waiterTable.getItems().remove(selectedWaiter);
            } else {
                System.out.println("Waiter not removed.");
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

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
