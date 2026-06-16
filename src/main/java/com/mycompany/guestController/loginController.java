package com.mycompany.guestController;

import com.mycompany.dao.DatabaseConnection;
import com.mycompany.dao.UserConnection;
import com.mycompany.model.CurrentUser;
import com.mycompany.managerController.newManagerController;
import com.mycompany.waiterController.waiter1Controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class loginController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private ImageView closeImage;
    Stage stage;
    Parent root;

    @FXML
    public void loginClose(MouseEvent event) {
        stage = (Stage) closeImage.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {
        usernameField.setOnKeyPressed(t -> {
            if (t.getCode() == KeyCode.DOWN) {
                passwordField.requestFocus();
            }
        });

        passwordField.setOnKeyPressed(t -> {
            if (t.getCode() == KeyCode.UP) {
                usernameField.requestFocus();
            }
        });
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

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void login(ActionEvent event) {
        String uname = usernameField.getText();
        String pass = passwordField.getText();

        UserConnection UC = new UserConnection(); // unified DAO
        String role = UC.loginUser(uname, pass);

        if (uname.isEmpty() || pass.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Information");
            alert.setHeaderText("Login Incomplete");
            alert.setContentText("Please fill in the required fields.");
            alert.showAndWait();
            return;
        }

        if (role != null) {
            try {
                CurrentUser.setUsername(uname);
                FXMLLoader loader;
                Parent root;
                Scene scene = null;
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                if (role.equals("Manager")) {
                    loader = new FXMLLoader(getClass().getResource("/managerFxml/newManagerFxml.fxml"));
                    root = loader.load();
                    scene = new Scene(root);

                    newManagerController welcomeController = loader.getController();
                    try (Connection con = DatabaseConnection.getConnection()) {
                        PreparedStatement P = con.prepareStatement("SELECT f_name FROM User WHERE user_name = ?");
                        P.setString(1, uname);
                        ResultSet r = P.executeQuery();
                        if (r.next()) {
                            String fname = r.getString("f_name");
                            welcomeController.displayManager(fname);
                        }
                    }

                } else if (role.equals("Guest")) {
                    loader = new FXMLLoader(getClass().getResource("/guestFxml/guest1.fxml"));
                    root = loader.load();
                    scene = new Scene(root);

                    guest1Controller welcomeController = loader.getController();
                    try (Connection con = DatabaseConnection.getConnection()) {
                        PreparedStatement P = con.prepareStatement("SELECT f_name FROM User WHERE user_name = ?");
                        P.setString(1, uname);
                        ResultSet r = P.executeQuery();
                        if (r.next()) {
                            String fname = r.getString("f_name");
                            welcomeController.displayGuest(fname);
                        }
                    }
                } else if (role.equals("Waiter")) {
                    loader = new FXMLLoader(getClass().getResource("/waiterFxml/waiter1.fxml"));
                    root = loader.load();
                    scene = new Scene(root);

                    waiter1Controller welcomeController = loader.getController();
                    try (Connection con = DatabaseConnection.getConnection()) {
                        PreparedStatement P = con.prepareStatement("SELECT f_name FROM User WHERE user_name = ?");
                        P.setString(1, uname);
                        ResultSet r = P.executeQuery();
                        if (r.next()) {
                            String fname = r.getString("f_name");
                            welcomeController.displayWaiter(fname);
                        }
                    }
                }
                stage.setScene(scene);
                stage.close();

                Stage newStage = new Stage();
                newStage.setScene(scene);
                newStage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Hotel.png")));
                newStage.setTitle("Hotel Management System");
                newStage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login");
            alert.setContentText("Login Failed.");
            alert.showAndWait();
            System.out.println("Login failed.");
        }
    }
}
