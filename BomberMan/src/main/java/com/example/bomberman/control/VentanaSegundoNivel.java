package com.example.bomberman.control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.bomberman.screens.ScreenB;

public class VentanaSegundoNivel implements Initializable {

    @FXML
    private Canvas canvas;

    private GraphicsContext graphicsContext;
    private ScreenB screenB;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.graphicsContext = canvas.getGraphicsContext2D();
        this.screenB = new ScreenB(this.canvas);

        Image backgroundImage = new Image(getClass().getResourceAsStream("/map/mapa_50.png"));

        graphicsContext.drawImage(backgroundImage, 0, 0);

        this.canvas.setOnKeyPressed(event -> {
            screenB.onKeyPressed(event);
        });

        this.canvas.setOnKeyReleased(event -> {
            screenB.onKeyReleased(event);
        });

        new Thread(
                () -> {
                    while (true) {

                        Platform.runLater(() -> {
                            screenB.paint(backgroundImage);

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
