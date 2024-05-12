package com.example.bomberman.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class Obstacle extends Position {
    private double x;
    private double y;
    private double width;
    private double height;


    public Obstacle(double x, double y, double width, double height, Canvas canvas) {
        super(x, y, canvas);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean intersects(double otherX, double otherY, double otherWidth, double otherHeight) {
        double scaledX = getX();
        double scaledY = getY();
        double scaledWidth = width;
        double scaledHeight = height;


        return scaledX < otherX + otherWidth &&
                scaledX + scaledWidth > otherX &&
                scaledY < otherY + otherHeight &&
                scaledY + scaledHeight > otherY;



    }

    public Color getColor() {
        return Color.GREEN;
    }

}