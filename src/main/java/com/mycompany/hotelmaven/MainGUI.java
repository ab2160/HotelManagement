package com.mycompany.hotelmaven;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainGUI extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/guestFxml/homePage.fxml"));
            Scene scene = new Scene(root);
            String css = getClass().getResource("/CSS/Style.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);

            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Hotel.png")));
            stage.setTitle("Hotel Management System");

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
