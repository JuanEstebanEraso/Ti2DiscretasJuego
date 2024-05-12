package com.example.bomberman.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ObstacleToBreak implements Breakable {
    private double x;
    private double y;
    private double width;
    private double height;
    private Image obstacleToBreakImage;

    public ObstacleToBreak(double x, double y, double width, double height, GraphicsContext graphicsContext) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.obstacleToBreakImage = new Image(getClass().getResourceAsStream("/Box/Box_01.png"));

    }

    public Image getCurrentImage() {
        return obstacleToBreakImage;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
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

    public void move(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    public boolean intersects(double otherX, double otherY, double otherWidth, double otherHeight) {
        return x < otherX + otherWidth &&
                x + width > otherX &&
                y < otherY + otherHeight &&
                y + height > otherY;
    }

    @Override
    public void breakObstacle() {

    }


}