package com.example.bomberman.control;

import com.example.bomberman.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class ventanaPortadaController {

    @FXML
    private ImageView PortadaImage;

    @FXML
    public void onStartGameButtonClick(ActionEvent event) {
        HelloApplication.hideWindow((Stage) PortadaImage.getScene().getWindow());
        HelloApplication.showWindow("VentanaJuego.fxml", 1200, 800);

    }

}
