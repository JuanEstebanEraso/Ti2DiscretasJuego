package com.example.bomberman.control;

import  javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import com.example.bomberman.screens.ScreenC;
import javafx.application.Platform;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;

public class VentanaTercerNivelController implements Initializable {

    @FXML
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private ScreenC screenC;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.graphicsContext = canvas.getGraphicsContext2D();
        this.screenC = new ScreenC(this.canvas);


        Image backgroundImage = new Image(getClass().getResourceAsStream("/map/mapa_80.png"));

        graphicsContext.drawImage(backgroundImage, 0, 0);


        this.canvas.setOnKeyPressed(event -> {
            screenC.onKeyPressed(event);

        });

        this.canvas.setOnKeyReleased(event -> {
            screenC.onKeyReleased(event);
        });

        // Hilo de Java
        new Thread(
                () -> {
                    while (true) {

                        Platform.runLater(() -> {
                            screenC.paint(backgroundImage);

                        });

                        try {
                            Thread.sleep(70);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

    }

}
