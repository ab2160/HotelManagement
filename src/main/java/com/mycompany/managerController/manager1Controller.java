package com.mycompany.managerController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class manager1Controller {

    @FXML
    private Label welcomelabel;
    Parent root;
    Stage stage;

    @FXML
    public void displayManager(String name) {
        welcomelabel.setText("Welcome Manager " + name);
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
    public void switchToguest1(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/guestFxml/guest1.fxml"));
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
    public void switchToguest2(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/guestFxml/guest2.fxml"));
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
    public void switchToguest5(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/guestFxml/guest5.fxml"));
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
