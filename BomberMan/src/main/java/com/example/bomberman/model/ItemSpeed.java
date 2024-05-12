package com.example.bomberman.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ItemSpeed {
    private BomberMan bomberman;
    private double x;
    private double y;
    private boolean collected;
    private double width;
    private Image image;
    private double height;
    private boolean recogido;



    public ItemSpeed(double x, double y, double width, double height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
        this.collected = false;
    }
    public boolean intersects(double otherX, double otherY, double otherWidth, double otherHeight) {
        return x < otherX + otherWidth &&
                x + width > otherX &&
                y < otherY + otherHeight &&
                y + height > otherY;
    }
    public void paint(GraphicsContext graphicsContext) {
        if (!recogido) {
            graphicsContext.drawImage(image, x, y, width, height);
        }
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public boolean isCollected() {
        return collected;
    }
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void collect() {
        collected = true;

    }
    public void setPosition(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }

    public void setBomberMan(BomberMan bomberman) {
        this.bomberman = bomberman;
    }
    public boolean isRecogido(){
        return recogido;
    }
    public void setRecogido(boolean recogido) {
        this.recogido = recogido;
    }
}



