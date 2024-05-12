package com.example.bomberman.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ItemSpeedRepresentation {

    private double x;
    private double y;
    private double width;
    private double height;
    private Image image;

    public ItemSpeedRepresentation(double x, double y, double width, double height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public void paint(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, x, y, width, height);
    }

}
