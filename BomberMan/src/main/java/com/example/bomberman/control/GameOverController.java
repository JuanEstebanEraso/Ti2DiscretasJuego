package com.example.bomberman.control;

import com.example.bomberman.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class GameOverController {

    @FXML
    private ImageView GameOverImage;

    @FXML
    public void onStartGameButtonClick(ActionEvent event) {
        // Cerrar la ventana actual
        Stage currentStage = (Stage) GameOverImage.getScene().getWindow();
        currentStage.close();

        // Mostrar una nueva ventana del juego
        try {
            HelloApplication.showWindow("VentanaJuego.fxml", 1200, 800);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}