package com.example.bomberman.model;

import javafx.scene.image.Image;

import javafx.scene.canvas.GraphicsContext;

public class Button {

    private BomberMan bomberman;

    private boolean isDoorOpen;

    private int x;

    private int y;

    private double width;

    private double height;

    private Image image;

    private boolean isPressed;

    public Button(boolean isDoorOpen, int x, int y, double width, double height, Image image,
            boolean isPressed) {
        this.isDoorOpen = false;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
        this.isPressed = isPressed;

    }

    public boolean isPressed(Position bomberManPosition) {
        return isBomberManNear(bomberManPosition);
    }



    private boolean isBomberManNear(Position bomberManPosition) {

        double bomberManHeight = bomberman.getHeight();
        double bomberManWidth = bomberman.getWidth();


        return bomberManPosition.getX() + bomberManWidth >= x &&
                bomberManPosition.getX() <= x + width &&
                bomberManPosition.getY() + bomberManHeight >= y &&
                bomberManPosition.getY() <= y + height;
    }

    public void paint(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, x, y, width, height);
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

    public double getHeight() {
        return height;
    }

    public boolean isDoorOpen() {
        return isDoorOpen;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public void setPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public void setBomberMan(BomberMan bomberman) {
        this.bomberman = bomberman;
    }

}
