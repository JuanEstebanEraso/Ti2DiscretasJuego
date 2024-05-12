package com.example.bomberman.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Position {

    protected double x;
    private double y;

    private Canvas canvas;

    private Punto position;
    protected GraphicsContext CP;

    public Position(double x, double y, Canvas canvas) {

        this.position = new Punto(x, y);

        this.canvas = canvas;

        this.CP = canvas.getGraphicsContext2D();

    }

    public Punto getPosition() {
        return position;
    }

    public void setPosition(Punto position) {
        this.position = position;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public double getX() {
        return position.getX();
    }

    public void setX(double x) {
        this.position.setX(x);
    }

    public double getY() {
        return position.getY();
    }

    public void setY(double y) {
        this.position.setY(y);
        ;
    }

    public double distance(Position punto) {
        return Math.sqrt(
                Math.pow(this.x - punto.getX(), 2) +
                        Math.pow(this.y - punto.getY(), 2));
    }

    public GraphicsContext getGraphicsContext2D() {
        return CP;
    }

}