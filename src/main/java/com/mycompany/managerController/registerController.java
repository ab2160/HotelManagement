package com.mycompany.managerController;

import com.mycompany.dao.ManagerConnection;
import com.mycompany.model.Manager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class registerController {

    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField phoneField;
    @FXML
    private ImageView closeIcon2;
    Stage stage;
    Parent root;

    @FXML
    public void registerClose(MouseEvent event) {
        stage = (Stage) closeIcon2.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {
        firstnameField.setOnKeyPressed((KeyEvent t) -> {
            if (t.getCode() == KeyCode.DOWN) {
                lastnameField.requestFocus();
            }
        });
        lastnameField.setOnKeyPressed((KeyEvent t) -> {
            if (t.getCode() == KeyCode.UP) {
                firstnameField.requestFocus();
            } else if (t.getCode() == KeyCode.DOWN) {
                usernameField.requestFocus();
            }
        });
        usernameField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                lastnameField.requestFocus();
            } else if (e.getCode() == KeyCode.DOWN) {
                passwordField.requestFocus();
            } else if (e.getCode() == KeyCode.RIGHT) {
                passwordField.requestFocus();
            }
        });
        passwordField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                usernameField.requestFocus();
            } else if (e.getCode() == KeyCode.DOWN) {
                phoneField.requestFocus();
            }
        });
        phoneField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                passwordField.requestFocus();
            } else if (e.getCode() == KeyCode.LEFT) {
                firstnameField.requestFocus();
            }
        });
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

            stage.setScene(scene);
            stage.show();

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

            stage.close();
            Stage managerStage = new Stage();
            managerStage.setScene(scene);

            managerStage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Hotel.png")));
            managerStage.setTitle("Hotel Management System");
            managerStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void registerManager(ActionEvent event) {
        try {
            String fname = firstnameField.getText();
            String lname = lastnameField.getText();
            String uname = usernameField.getText();
            String pass = passwordField.getText();
            String phone = phoneField.getText();

            if (fname.isEmpty() || lname.isEmpty() || uname.isEmpty() || pass.isEmpty() || phone.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Information");
                alert.setHeaderText("Registration Incomplete");
                alert.setContentText("Please fill in the required fields.");
                alert.showAndWait();
                return;
            }
            
            int managerid = 0;
            Manager M = new Manager(managerid, fname, lname, uname, pass, phone, "Manager");
            ManagerConnection MC = new ManagerConnection();
            boolean success = MC.saveManagerData(M);
            if (success) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Registration");
                alert.setHeaderText("Manager registered successfully.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registration");
                alert.setHeaderText("Registration failed.");
                alert.showAndWait();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
