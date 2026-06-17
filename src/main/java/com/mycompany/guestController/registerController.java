package com.mycompany.guestController;

import com.mycompany.dao.DatabaseConnection;
import com.mycompany.dao.GuestConnection;
import com.mycompany.model.CurrentUser;  
import com.mycompany.model.Guest;
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
import javafx.scene.control.TextFormatter;
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
    private ImageView closeIcon1;
    Stage stage;
    Parent root;

    @FXML
    public void registerClose(MouseEvent event) {
        stage = (Stage) closeIcon1.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {
        phoneField.setTextFormatter(new TextFormatter<>(change ->
        change.getControlNewText().matches("\\d*") ? change : null));
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
    public void switchTohome(MouseEvent event) {
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
    public void registerGuest(ActionEvent event) {
        try {
            String fname = firstnameField.getText();
            String lname = lastnameField.getText();
            String uname = usernameField.getText();
            String pass = passwordField.getText();
            String phone = phoneField.getText();

            if(fname.isEmpty() || lname.isEmpty() || uname.isEmpty() || pass.isEmpty() || phone.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Information");
                alert.setHeaderText("Registration Incomplete");
                alert.setContentText("Please fill in the required fields.");
                alert.showAndWait();
                return;
            }
            int guestid = 0;
            Guest G = new Guest(guestid, fname, lname, uname, pass, phone);
            GuestConnection GC = new GuestConnection();
            boolean success = GC.saveGuestData(G);
            if (success) {
                CurrentUser.setUsername(uname); 
                System.out.println("Guest registered successfully.");
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/guestFxml/guest1.fxml"));
                    root = loader.load();
                    Scene scene = new Scene(root);

                    guest1Controller welcomeController = loader.getController();
                    try (Connection con = DatabaseConnection.getConnection()) {
                        PreparedStatement P = con.prepareStatement(
                                "SELECT g.guest_id, u.f_name "
                                + "FROM Guest g "
                                + "JOIN User u ON g.user_name = u.user_name "
                                + "WHERE g.user_name = ?");
                        P.setString(1, uname);
                        ResultSet r = P.executeQuery();
                        if (r.next()) {
                            fname = r.getString("f_name");
                            int guestId = r.getInt("guest_id");
                            welcomeController.displayGuest(fname);
                            welcomeController.setCurrentGuestId(guestId);
                        }
                    }
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);

                    String css = getClass().getResource("/CSS/Style.css").toExternalForm();
                    scene.getStylesheets().add(css);

                    stage.close();
                    Stage guestStage = new Stage();
                    guestStage.setScene(scene);
                    
                    guestStage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Hotel.png")));
                    guestStage.setTitle("Hotel Management System");
                    guestStage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Registration");
                alert.setContentText("User with this username already exists.");
                alert.showAndWait();
                System.out.println("Registration failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
