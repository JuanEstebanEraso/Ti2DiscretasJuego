package com.example.bomberman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("VentanaPortada.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void showWindow(String fxml, double width, double height) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    HelloApplication.class.getResource("/com/example/bomberman/" + fxml));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, width, height);
            Stage stage = new Stage();
            stage.setTitle(" BomberMan!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void hideWindow(Stage stage) {
        stage.hide();
    }


    public static void main(String[] args) {
        launch();
    }
}