package com.example.bomberman.model;

public class Punto {
    private double X;
    private double Y;

    public Punto(double X, double Y) {
        this.X = X;
        this.Y = Y;
    }

    public void normalize() {
        double normal = Math.sqrt(Math.pow(X, 2) + Math.pow(Y, 2));
        if (normal != 0) {
            X /= normal;
            Y /= normal;
        }
    }

    public void setSpeed(int speed) {
        X *= speed;
        Y *= speed;
    }

    public void add(Punto punto) {
        this.X += punto.X;
        this.Y += punto.Y;
    }

    public double getX() {
        return X;
    }

    public void setX(double X) {
        this.X = X;
    }

    public double getY() {
        return Y;
    }

    public void setY(double Y) {
        this.Y = Y;
    }
}