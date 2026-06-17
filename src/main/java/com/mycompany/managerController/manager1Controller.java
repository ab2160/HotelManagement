package com.mycompany.managerController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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

            homeStage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Hotel.png")));
            homeStage.setTitle("Hotel Management System");
            homeStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToregister(ActionEvent event) {

        switchscene_UNDECORATED(event, "/guestFxml/registerScene.fxml");
    }

    @FXML
    public void switchTologin(ActionEvent event) {

        switchscene_UNDECORATED(event, "/guestFxml/loginScene.fxml");
    }

    @FXML
    public void switchToManagerregister(ActionEvent event) {

        switchscene_UNDECORATED(event, "/managerFxml/registerManager.fxml");

    }

    @FXML
    public void switchscene_UNDECORATED(ActionEvent event, String path) {

        try {
            root = FXMLLoader.load(getClass().getResource(path));
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
    private void switchScene(ActionEvent event, String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);

            String css = getClass().getResource("/CSS/Style.css").toExternalForm();
            scene.getStylesheets().add(css);

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void switchToremoveUser(ActionEvent event) {

        switchScene(event, "/managerFxml/removeUser.fxml");
    }

    @FXML
    public void switchToaddRoomandService(ActionEvent event) {

        switchScene(event, "/managerFxml/addRoomandService.fxml");
    }

    @FXML
    public void switchToguest1(ActionEvent event) {

        switchScene(event, "/guestFxml/guest1.fxml");

    }

    @FXML
    public void switchToremoveRoomandService(ActionEvent event) {

        switchScene(event, "/managerFxml/removeRoomandService.fxml");
    }

    @FXML
    public void switchToguest2(ActionEvent event) {

        switchScene(event, "/guestFxml/guest2.fxml");
    }

    @FXML
    public void switchToguest5(ActionEvent event) {

        switchScene(event, "/guestFxml/guest5.fxml");
    }

    

}
