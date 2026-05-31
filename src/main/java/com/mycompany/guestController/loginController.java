package com.mycompany.guestController;

import com.mycompany.dao.DatabaseConnection;
import com.mycompany.dao.UserConnection;
import com.mycompany.managerController.manager1Controller;
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
import javafx.scene.control.TextField;
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

        if (role != null) {
            try {
                FXMLLoader loader;
                Parent root;
                Scene scene = null;
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                if (role.equals("Manager")) {
                    loader = new FXMLLoader(getClass().getResource("/managerFxml/manager1.fxml"));
                    root = loader.load();
                    scene = new Scene(root);

                    manager1Controller welcomeController = loader.getController();
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
                Stage guestStage = new Stage();
                guestStage.setScene(scene);
                guestStage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Login failed.");
        }
    }
}
