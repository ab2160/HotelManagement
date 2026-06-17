package com.mycompany.managerController;

import com.mycompany.dao.DatabaseConnection;
import com.mycompany.model.AvailableInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class staffSidebarController {

    @FXML
    private TableView<AvailableInfo> userTable;
    @FXML
    private TableColumn<AvailableInfo, String> colUserName;
    @FXML
    private TableColumn<AvailableInfo, String> colFirstName;
    @FXML
    private TableColumn<AvailableInfo, String> colLastName;
    @FXML
    private TableColumn<AvailableInfo, String> colPhoneNumber;
    Parent root;
    Stage stage;

    @FXML
    public void initialize() {
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));

        ObservableList<AvailableInfo> info = FXCollections.observableArrayList();

        try (Connection con = DatabaseConnection.getConnection()) {
            String available = "SELECT user_name, f_name, l_name, phone_num FROM User WHERE Role = 'Waiter'";
            PreparedStatement P = con.prepareStatement(available);
            ResultSet r = P.executeQuery();

            while (r.next()) {
                AvailableInfo information = new AvailableInfo(
                        r.getString("user_name"),
                        r.getString("f_name"),
                        r.getString("l_name"),
                        r.getString("phone_num"));
                info.add(information);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        userTable.setItems(info);
    }

    @FXML
    public void switchToNewmanager(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/managerFxml/newManagerFxml.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);

            newManagerController managerController = loader.getController();
            managerController.setCurrrentManagerId(Session.getManagerId());
            managerController.displayManager(Session.getManagerName());

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
