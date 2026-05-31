package com.mycompany.waiterController;

import com.mycompany.dao.ManagerConnection;
import com.mycompany.dao.WaiterConnection;
import com.mycompany.managerController.manager1Controller;
import com.mycompany.model.Manager;
import com.mycompany.model.Waiter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private ImageView closeIcon3;
    Stage stage;
    Parent root;

    @FXML
    public void registerClose(MouseEvent event) {
        stage = (Stage) closeIcon3.getScene().getWindow();
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
            homeStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void registerWaiter(ActionEvent event) {
        try {
            String fname = firstnameField.getText();
            String lname = lastnameField.getText();
            String uname = usernameField.getText();
            String pass = passwordField.getText();
            String phone = phoneField.getText();

            int waiterid = 0;
            Manager M = new Manager(waiterid, fname, lname, uname, pass, phone, "Waiter");
            ManagerConnection MC = new ManagerConnection();
            boolean success = MC.saveManagerData(M);

            if (success) {
                System.out.println("Waiter registered successfully.");
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/waiterFxml/waiter1.fxml"));
                    root = loader.load();
                    Scene scene = new Scene(root);

                    waiter1Controller welcomeController = loader.getController();
                    welcomeController.displayWaiter(fname);
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);

                    String css = getClass().getResource("/CSS/Style.css").toExternalForm();
                    scene.getStylesheets().add(css);

                    stage.setScene(scene);
                    stage.close();
                    Stage waiterStage = new Stage();
                    waiterStage.setScene(scene);
                    waiterStage.show();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Waiter not registered.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
